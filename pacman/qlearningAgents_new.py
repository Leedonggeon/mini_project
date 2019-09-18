  #############################
my_univ_id = "201147079"     ## Univ. Num.
my_password = "01020946745"   ## Your Phone Number
#############################

# qlearningAgents.py
# ------------------
# Licensing Information:  You are free to use or extend these projects for
# educational purposes provided that (1) you do not distribute or publish
# solutions, (2) you retain this notice, and (3) you provide clear
# attribution to UC Berkeley, including a link to
# http://inst.eecs.berkeley.edu/~cs188/pacman/pacman.html
#
# Attribution Information: The Pacman AI projects were developed at UC Berkeley.
# The core projects and autograders were primarily created by John DeNero
# (denero@cs.berkeley.edu) and Dan Klein (klein@cs.berkeley.edu).
# Student side autograding was added by Brad Miller, Nick Hay, and
# Pieter Abbeel (pabbeel@cs.berkeley.edu).


from game import *
from learningAgents import ReinforcementAgent
from featureExtractors import *

import random,util,math

debug = True

class QLearningAgent(ReinforcementAgent):
    """
      Q-Learning Agent

      Functions you should fill in:
        - computeValueFromQValues
        - computeActionFromQValues
        - getQValue
        - getAction
        - update

      Instance variables you have access to
        - self.epsilon (exploration prob)
        - self.alpha (learning rate)
        - self.discount (discount rate)

      Functions you should use
        - self.getLegalActions(state)
          which returns legal actions for a state
    """
    def __init__(self, **args):
        "You can initialize Q-values here,,,"
        ReinforcementAgent.__init__(self, **args)
        if debug == True:
            print("ALPHA", self.alpha)
            print("DISCOUNT", self.discount)
            print("EXPLORATION", self.epsilon)
        self.qValues = util.Counter()

    def getQValue(self, state, action):
        """
          Returns Q(state,action)
          Should return 0.0 if we have never seen a state
          or the Q node value otherwise
        """

        "*** YOUR CODE HERE ***"
        if (state, action) not in self.qValues:
            return 0.0
        #otherwise, we return the Q value of the current Q node
        else:
            return self.qValues[(state, action)]



    def computeValueFromQValues(self, state):
        """
          Returns max_action Q(state,action)
          where the max is over legal actions.  Note that if
          there are no legal actions, which is the case at the
          terminal state, you should return a value of 0.0.
        """

        "*** YOUR CODE HERE ***"
        qVal = []
        for action in self.getLegalActions(state):
            qVal.append(self.getQValue(state, action))
        #if there are no legal actions, we return 0.0
        if len(qVal) == 0:
            return 0.0
        #otherwise, we return the max of all of the legal actions for the current state
        else:
            return max(qVal)

    def computeActionFromQValues(self, state):
        """
          Compute the best action to take in a state.  Note that if there
          are no legal actions, which is the case at the terminal state,
          you should return None.
        """

        "*** YOUR CODE HERE ***"
        val = self.getValue(state)
        for action in self.getLegalActions(state):
            if self.getQValue(state, action) == val:
                bestaction = action
        if len(bestaction) == 0:
            return 0.0
        else:
            return bestaction

    def getAction(self, state):
        """
          Compute the action to take in the current state.  With
          probability self.epsilon, we should take a random action and
          take the best policy action otherwise.  Note that if there are
          no legal actions, which is the case at the terminal state, you
          should choose None as the action.

          HINT: You might want to use util.flipCoin(prob)
          HINT: To pick randomly from a list, use random.choice(list)
        """
        # Pick Action
        legalActions = self.getLegalActions(state)
        action = None

        "*** YOUR CODE HERE ***"
        if len(legalActions) == 0:
            return action
        if(util.flipCoin(self.epsilon)):
          return random.choice(legalActions)
        else : 
          return self.computeActionFromQValues(state)

    def update(self, state, action, nextState, reward):
        """
          The parent class calls this to observe a
          state = action => nextState and reward transition.
          You should do your Q-Value update here

          NOTE: You should never call this function,
          it will be called on your behalf
        """
        if debug == True:
            print("State: ", state, " , Action: ", action, " , NextState: ", nextState, " ,) Reward: ", reward)
            print("QVALUE", self.getQValue(state, action))
            print("VALUE", self.getValue(nextState))

        "*** YOUR CODE HERE ***"
        #Q'(s,a) = Q(s,a) + a*(r + discount*vQ(s',a') - Q(s, a))
        Qval = self.qValues[(state, action)]
        val = self.alpha*(reward + (self.discount * self.computeValueFromQValues(nextState)))
        self.qValues[state, action] = (1-self.alpha)*Qval + val

    def getPolicy(self, state):
        return self.computeActionFromQValues(state)

    def getValue(self, state):
        return self.computeValueFromQValues(state)


class PacmanQAgent(QLearningAgent):
    "Exactly the same as QLearningAgent, but with different default parameters"

    def __init__(self, epsilon=0.05,gamma=0.8,alpha=0.2, numTraining=0, **args):
        """
        These default parameters can be changed from the pacman.py command line.
        For example, to change the exploration rate, try:
            python pacman.py -p PacmanQLearningAgent -a epsilon=0.1

        alpha    - learning rate
        epsilon  - exploration rate
        gamma    - discount factor
        numTraining - number of training episodes, i.e. no learning after these many episodes
        """
        args['epsilon'] = epsilon
        args['gamma'] = gamma
        args['alpha'] = alpha
        args['numTraining'] = numTraining
        self.index = 0  # This is always Pacman
        QLearningAgent.__init__(self, **args)

    def getAction(self, state):
        """
        Simply calls the getAction method of QLearningAgent and then
        informs parent of action for Pacman.  Do not change or remove this
        method.
        """
        action = QLearningAgent.getAction(self,state)
        self.doAction(state,action)
        return action


class ApproximateQAgent(PacmanQAgent):
    """
       ApproximateQLearningAgent

       You should only have to overwrite getQValue
       and update.  All other QLearningAgent functions
       should work as is.
    """
    def __init__(self, extractor='IdentityExtractor', **args):
        self.featExtractor = util.lookup(extractor, globals())()
        PacmanQAgent.__init__(self, **args)
        self.weights = util.Counter()

    def getWeights(self):
        return self.weights

    def getQValue(self, state, action):
        """
          Should return Q(state,action) = w * featureVector
          where * is the dotProduct operator
        """

        "*** YOUR CODE HERE ***"
        # Q(s,a) = sigma fi(s,a)wi
        weight = self.getWeights()
        featureVector = self.featExtractor.getFeatures(state, action)
        Q = weight * featureVector

        return Q

    def update(self, state, action, nextState, reward):
        """
           Should update your weights based on transition
        """
        "*** YOUR CODE HERE ***"
        self.weights = self.getWeights()
        feature = self.featExtractor.getFeatures(state, action)
        self.difference = (reward + self.discount * self.getValue(nextState)) - self.getQValue(state, action)

        for i in feature :
          self.weights[i] = self.weights[i] + (self.alpha * self.difference * feature[i])

    def final(self, state):
        "Called at the end of each game."

        # Set id and password
        if state.univ_id == '' and state.password == '':
            state.univ_id = my_univ_id
            state.password = my_password

        # call the super-class final method
        PacmanQAgent.final(self, state)

        # did we finish training?
        if self.episodesSoFar == self.numTraining:
            # you might want to print your weights here for debugging
            "*** YOUR CODE HERE (Use only when you need it. Not required)***"
            pass
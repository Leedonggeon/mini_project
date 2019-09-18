# featureExtractors.py
# --------------------
# Licensing Information:  You are free to use or extend these projects for
# educational purposes provided that (1) you do not distribute or publish
# solutions, (2) you retain this notice, and (3) you provide clear
# attribution to UC Berkeley, including a link to http://ai.berkeley.edu.
#
# Attribution Information: The Pacman AI projects were developed at UC Berkeley.
# The core projects and autograders were primarily created by John DeNero
# (denero@cs.berkeley.edu) and Dan Klein (klein@cs.berkeley.edu).
# Student side autograding was added by Brad Miller, Nick Hay, and
# Pieter Abbeel (pabbeel@cs.berkeley.edu).


"Feature extractors for Pacman game states"

from game import Directions, Actions
import util

class FeatureExtractor:
    def getFeatures(self, state, action):
        """
          Returns a dict from features to counts
          Usually, the count will just be 1.0 for
          indicator functions.
        """
        util.raiseNotDefined()

class IdentityExtractor(FeatureExtractor):
    def getFeatures(self, state, action):
        feats = util.Counter()
        feats[(state,action)] = 1.0
        return feats

class CoordinateExtractor(FeatureExtractor):
    def getFeatures(self, state, action):
        feats = util.Counter()
        feats[state] = 1.0
        feats['x=%d' % state[0]] = 1.0
        feats['y=%d' % state[0]] = 1.0
        feats['action=%s' % action] = 1.0
        return feats

def closestFood(pos, food, walls):
    """
    closestFood -- this is similar to the function that we have
    worked on in the search project; here its all in one place
    """
    fringe = [(pos[0], pos[1], 0)]
    expanded = set()
    while fringe:
        pos_x, pos_y, dist = fringe.pop(0)
        if (pos_x, pos_y) in expanded:
            continue
        expanded.add((pos_x, pos_y))
        # if we find a food at this location then exit
        if food[pos_x][pos_y]:
            return dist
        # otherwise spread out from the location to its neighbours
        nbrs = Actions.getLegalNeighbors((pos_x, pos_y), walls)
        for nbr_x, nbr_y in nbrs:
            fringe.append((nbr_x, nbr_y, dist+1))
    # no food found
    return None

class SimpleExtractor(FeatureExtractor):
    """
    Returns simple features for a basic reflex Pacman:
    - whether food will be eaten
    - how far away the next food is
    - whether a ghost collision is imminent
    - whether a ghost is one step away
    """

    def getFeatures(self, state, action):
        # extract the grid of food and wall locations and get the ghost locations
        food = state.getFood()
        walls = state.getWalls()
        ghosts = state.getGhostPositions()
        ghostsStates = state.getGhostStates()

        features = util.Counter()

        features["bias"] = 1.0

        # compute the location of pacman after he takes the action
        x, y = state.getPacmanPosition()
        dx, dy = Actions.directionToVector(action)
        next_x, next_y = int(x + dx), int(y + dy)

        # count the number of ghosts 1-step away
        features["#-of-ghosts-1-step-away"] = sum((next_x, next_y) in Actions.getLegalNeighbors(g, walls) for g in ghosts)

        # if there is no danger of ghosts then add the food feature
        if not features["#-of-ghosts-1-step-away"] and food[next_x][next_y]:
            features["eats-food"] = 1.0

        dist = closestFood((next_x, next_y), food, walls)
        if dist is not None:
            # make the distance a number less than one otherwise the update
            # will diverge wildly
            features["closest-food"] = float(dist) / (walls.width * walls.height)
        features.divideAll(10.0)
        return features


# diffrence with Simple is eat  the scared ghost.
# eat and stunned...
# to go dangerous place to eat ghost
class CustomExtractor(FeatureExtractor):
    """
    Generate your own feature
    """
    def getFeatures(self, state, action):
        "*** YOUR CODE HERE ***"
        food = state.getFood()
        walls = state.getWalls()
        ghosts = state.getGhostPositions()
        ghostsStates = state.getGhostStates()
        capsules = state.getCapsules()

        features = util.Counter()
        features["bias"] = 1.0

# compute the location of pacman after he takes the action
        x, y = state.getPacmanPosition()
        dx, dy = Actions.directionToVector(action)
        next_x, next_y = int(x + dx), int(y + dy)
# 0.15, 0.15, 20, 20, 2, 5
        if((ghostsStates[0].scaredTimer > 0) and (ghostsStates[1].scaredTimer > 0)):
            features["scared-ghosts-1-step-away_0"] = 0.2*((next_x, next_y) in Actions.getLegalNeighbors(ghosts[0], walls))
            features["scared-ghosts-1-step-away_1"] = 0.2*((next_x, next_y) in Actions.getLegalNeighbors(ghosts[1], walls))
        else:        
            features["#-ghosts-1-step-away_0"] = (next_x, next_y) in Actions.getLegalNeighbors(ghosts[0], walls)
            features["#-ghosts-1-step-away_1"] = (next_x, next_y) in Actions.getLegalNeighbors(ghosts[1], walls)

        if(ghostsStates[0].scaredTimer > 0) and (ghostsStates[1].scaredTimer == 0):
            features["scared-ghosts-1-step-away_2"] = 20*((next_x, next_y) in Actions.getLegalNeighbors(ghosts[0], walls))
        else:
            features["#-ghosts-1-step-away_2"] = (next_x, next_y) in Actions.getLegalNeighbors(ghosts[1], walls)

        if(ghostsStates[1].scaredTimer > 0) and (ghostsStates[0].scaredTimer == 0):
            features["scared-ghosts-1-step-away_3"] = 20*((next_x, next_y) in Actions.getLegalNeighbors(ghosts[1], walls))
        else:
            features["#-ghosts-1-step-away_3"] = (next_x, next_y) in Actions.getLegalNeighbors(ghosts[0], walls)

        if((ghostsStates[0].scaredTimer > 0) and (ghostsStates[1].scaredTimer > 0)):
            features["scared-ghosts-1-step-away"] = 2*sum((next_x, next_y) in Actions.getLegalNeighbors(g, walls) for g in ghosts)
        else:
            features["#-of-ghosts-1-step-away"] = 5*sum((next_x, next_y) in Actions.getLegalNeighbors(g, walls) for g in ghosts)

        if not features["#-of-ghosts-1-step-away"] and food[next_x][next_y]:
            features["eats-food"] = 1.0

        dist_1 = closestFood((next_x, next_y), food, walls)
        if dist_1 is not None:
            features["closest-food"] = float(dist_1) / (walls.width * walls.height)

        (ghost0_x, ghost0_y) = ghosts[0]
        (ghost1_x, ghost1_y) = ghosts[1]
        distance0_x  = 0
        distance0_y = 0
        distance0_all = 0   
        distance1_x = 0
        distance1_y = 0 
        distance1_all = 0

        if(next_x - ghost0_x) >= 0:
            distance0_x = next_x - ghost0_x
        else:
            distance0_x = ghost0_x - next_x
        if(next_y - ghost0_y) >= 0:
            distance0_y = next_y - ghost0_y
        else:
            distance0_y = ghost0_y - next_y

        # distance of ghost0 and next_state
        distance0_all = distance0_x + distance0_y
        
        if(next_x - ghost1_x) >= 0:
            distance1_x = next_x - ghost1_x
        else:
            distance1_x = ghost1_x - next_x
        if(next_y - ghost1_y) >= 0:
            distance1_y = next_y - ghost1_y
        else:
            distance1_y = ghost1_y - next_y
        
        #distance of ghost1 and next_state
        distance1_all = distance1_x + distance1_y

        if((ghostsStates[0].scaredTimer > 0)):            
            features["ghost0_x"] = 0.1*((walls.width + walls.height) / (float(distance0_all) + 1))
        if((ghostsStates[1].scaredTimer > 0) and (ghostsStates[0].scaredTimer == 0)):
            features["ghost1_x"] = 0.1*((walls.width + walls.height) / (float(distance1_all) + 1))

        if (len(capsules) == 1):
            (capsules_x, capsules_y) = capsules[0]
        else:
            (capsules_x, capsules_y) = (0,0)    
        c_distance_x = 0
        c_distance_y = 0 
        c_distance_all = 0
        if(next_x - capsules_x) >= 0:
            c_distance_x = next_x - capsules_x
        else:
            c_distance_ydistance_x = capsules_x - next_x
        if(next_y - capsules_y) > 0:
            c_distance_y = next_y - capsules_y
        else:
            c_distance_y = capsules_y - next_y
        c_distance_all = c_distance_x + c_distance_y

        if (len(capsules) == 1):
            features["capsules0_x"] = 0.001*((walls.width + walls.height) / (float(c_distance_all) + 1))
        
        features.divideAll(10.0)
        return features

        # util.raiseNotDefined()

#include <iostream>
#include <fstream>
#include <string>
#include <math.h>
#include <vector>
using namespace std;

// page table's entry
struct entry{
	int validbit;
	int aid_entry;
};
// each Process have pid, entry table_entry.
// initialize valid bit = 1, aid_entry = -1.
class Process{
	public :
		int pid;
		entry table_entry[64];
		int check_aid(int aid);
		Process(){
			for(int i = 0; i < 64; i++){
				table_entry[i].validbit = -1;
				table_entry[i].aid_entry = -1;\
				}
		}
};
// count the number of entry with centain aid.
int Process::check_aid(int aid){
	int count = 0;
	for(int i = 0; i < 64; i++){
		if(table_entry[i].aid_entry==aid){
			count++;
		}
	}
	return count;
}
// LRU's factor. aid, start location, end location
struct factor{
	int LRU_aid;
	int LRU_st;
	int LRU_end;
};
// Phisical memory have cell that means frame's storage.
// cell[i] wii be initialized with -1.
class P_memory{
	public :
		int cell[32];
		int get_space(int volume, P_memory m);
		vector<factor> LRU;
		P_memory set_LRU(Process p, int aid, int start, int end, P_memory m);
		P_memory renew_LRU(Process p, int aid, P_memory m);
		P_memory remove_LRU(int aid, P_memory m);
		P_memory remove_LRU(P_memory m);
		P_memory set_cell(int a, int b, Process p, P_memory m);
		P_memory clean_cell(int a, P_memory m, int aid);
		P_memory(){
			for(int i = 0; i < 32 ; i++){
				cell[i] = -1;
			}
		}
};
//when i memory access the aid that is already existed
//we will just renew LRU. Do not change Physical memory
P_memory P_memory::renew_LRU(Process p, int aid, P_memory m){
	factor temp;
	for(int i = 0; i < m.LRU.size(); i++){
		if(m.LRU[i].LRU_aid == aid){
			temp = m.LRU[i];
			m = remove_LRU(aid, m);
			m = set_LRU(p, aid, temp.LRU_st, temp.LRU_end, m);
		}
	}
	return m;
}
// set the LRU.
P_memory P_memory::set_LRU(Process p, int aid, int start, int end, P_memory m){
	factor temp;
	for(int i=0; i < m.LRU.size(); i++){
		if(m.LRU[i].LRU_aid == aid){
			m = remove_LRU(aid, m);
		}
	}
	temp.LRU_aid = aid;
	temp.LRU_st = start;
	temp.LRU_end = end;
	m.LRU.push_back(temp);
	return m;
}
// when page fault occurs, remove the unit that have certain aid.
P_memory P_memory::remove_LRU(int aid, P_memory m){
	for(int i = 0; i < m.LRU.size(); i++){
		if(m.LRU[i].LRU_aid == aid){
			m.LRU.erase(m.LRU.begin()+i);
		}
	}
	return m;
}
// when page fault occurs, remove the least recently used unit.
P_memory P_memory::remove_LRU(P_memory m){
	m.LRU.erase(m.LRU.begin());
	return m;
}
// allocate the frame in physical memory
P_memory P_memory::set_cell(int volume, int aid, Process p, P_memory m){
		int start = 0;
		for(int i = 0; i < 32; i+=volume){
			if(m.cell[i] ==-1){
				for(int j = i; j< i+volume ; j++){
					m.cell[j] = aid;
				}
			start = i;
			break;		
			}
		}
		for(int i = 0; i < 64; i++){
			if(p.table_entry[i].aid_entry == aid){
				p.table_entry[i].validbit = 1;
			}
		}
		m =set_LRU(p, aid, start, start-1+volume, m);
		return m;
}
// remove the frame in physical memory. and remove LRU's first unit.
P_memory P_memory::clean_cell(int volume, P_memory m, int aid){
	for(int i = 0; i < 32; i++){
		if(m.LRU[0].LRU_aid == m.cell[i]){
			m.cell[i] = -1;
		}
	}
	m.LRU.erase(m.LRU.begin());
	return m;
}
// find whether memory storage is enough.
int P_memory::get_space(int volume, P_memory m){
	int check = -1;
	for(int i = 0; i < 32;  i+=volume){
		for(int j = i; j < i+volume ; j++){
			if(m.cell[j]!=-1){
				check = -1;
				break;
			}
			else{
				check = 0;
			}
		}
		if(check == 0){
			break;
		}
	}
	return check;
} 
// memory_access. calculate the number of page, and do memoery access.
P_memory memory_access(Process p, P_memory m, int aid){
	int p_num = 0;
	int p_volume = 0;
	int check;
	for(int i = 0; i < 64; i++){
		if(p.table_entry[i].aid_entry == aid){
			p_num++;
		}
	}
	if(p_num ==1){
		p_volume =1;
	}
	else{
	int degree = (int)(log(p_num-1)/log(2)) +1;
	p_volume = pow(2, degree);
	}
	//page size is bigger or equal than 4.
	if(p_num > 32){
		cout << "Error : page's size is bigger than Physical memory storage!" << endl;
		return m;
	}
	else{
		check = m.get_space(p_volume, m);
		while(check == -1){
			m = m.clean_cell(p_volume, m, aid);
			check = m.get_space(p_volume, m);
		}
		m = m.set_cell(p_volume, aid, p, m);
	}
	return m;
}
// allocate_pagetable
Process allocate_pagetable(Process p, int aid, int num_page){
	int num = num_page;
	for(int i = 0; i < 64 ; i++){
		if(num==0){
			break;
		}
		else{
			if(num > 64-i){
				cout << "Error : Don't have enough pages!" << endl;
				break;
			}
			if(p.table_entry[i].validbit == -1){
				p.table_entry[i].validbit = 0;
				p.table_entry[i].aid_entry = aid;
				num--;
			}
		}
	}
	return p;
}
// show the output.
void show_info(Process* p, P_memory m, int num_page, int aid, int pid, int func, int num_process){
	if(func == 1){
	cout << "* Input : Pid [" << pid << 
	"] Function [ALLOCATION] Alloc ID [" << aid << 
	"] Page Num[" << num_page << "]" << endl;
	}
	else if(func == 0){
	cout << "* Input : Pid [" << pid << 
	"] Function [ACCESS] Alloc ID [" << aid << 
	"] Page Num[" << num_page << "]" << endl;
	}
	cout << ">> Physical Memory : \t\t";
	for(int i = 0; i < 32; i++){
		if(i%4==0){
			cout << "|";		}
		if(m.cell[i]==-1){
			cout << "-";
		}
		else{
			cout << m.cell[i];
		}
	}
	cout <<"|"<<endl;
	for(int i = 0; i < num_process; i++){
		cout << ">> pid(" << p[i].pid << ") Page Table(AID) :\t";
		for(int j = 0; j < 64; j++){
			if(j%4==0){
				cout << "|";		
			}
			if(p[i].table_entry[j].aid_entry==-1){
				cout << "-";
			}
			else{
				cout << p[i].table_entry[j].aid_entry;
			}
		}
		cout <<"|"<<endl;
		cout << ">> pid(" << p[i].pid << ") Page Table(Valid) :\t";
		for(int j = 0; j < 64; j++){
			if(j%4==0){
				cout << "|";		
			}
			if(p[i].table_entry[j].validbit==-1){
				cout << "-";
			}
			else if(p[i].table_entry[j].validbit== 0){
				cout << "0";
			}
			else{
				cout << "1";
			}
		}
		cout <<"|"<<endl;
	}
	cout << ">> LRU : \t\t\t";
	for(int k = 0; k < m.LRU.size(); k++){
		cout << m.LRU[k].LRU_aid << ":" << m.LRU[k].LRU_st << "-" << m.LRU[k].LRU_end << " ";
	}
	cout << endl;
}
// main. get the num_process and produce the process object
// get the num_inst repeat the procedure num_inst times.
// also, it classify function is access or allocate, perform function.
// and count the page fault.
int main(int argc, char* argv[]){
	int num_process, num_inst;
	int pid, aid, num_page, func;
	int count = 0;
	int page_falut= 0;
	Process* p;
	P_memory pm;
	cin >> num_process;
	if(num_process > 4 || num_process < 0){
		cout << "Error: number of process is invalid!" << endl;
		return 0;
	}
	p = new Process[num_process];
	// pid initialization
	for(int i = 0; i < num_process; i++){
		p[i].pid = i;
	}
	cin >> num_inst;
	for(int i = 0; i < num_inst; i++){
		cin >> pid >> func;
		if(pid>num_process-1 || pid < 0){
			cout << "Error : pid is invalid!" << endl;
		}
		else{				
			if(func == 1){
				cin >> aid >> num_page;
				for(int j = 0; j < num_process; j++){
					count += p[j].check_aid(aid);
				}
				if(count==0){
					if(aid < 0){
						cout << "Error : Aid is invalid!" << endl;
					}
					else{
						p[pid] = allocate_pagetable(p[pid], aid, num_page);
					}
				}
				else{
					cout << "Error : Aid is already existing!" << endl;
				}
			}
			else if(func == 0){
				cin >> aid;
				for(int j = 0; j < num_process; j++){
					count = count + p[j].check_aid(aid);
				}
				num_page = count;
				count = 0;
				for(int j = 0 ; j < 32; j++){
					if(pm.cell[j]==aid){
						count = 1;
						break;
					}
				}
				if(count ==1){
					pm = pm.renew_LRU(p[pid],aid, pm);
				}
				else{
					if(aid < 0){
						cout << "Error : aid is invalid!" << endl;
					}
					else if(p[pid].check_aid(aid)==0){
						cout<< endl;
						cout << "Error : Aid "<<aid<< " doesnt' exist in this process page table!" << endl;
						cout<< endl;
					}
					else{
						page_falut++;
						pm = memory_access(p[pid], pm, aid);
						for(int i = 0; i < num_process; i++){
							for(int j = 0; j < 64; j++){
								p[i].table_entry[j].validbit = 0;
								for(int k = 0; k < pm.LRU.size(); k++){
									if(p[i].table_entry[j].aid_entry == pm.LRU[k].LRU_aid){
										p[i].table_entry[j].validbit = 1;
									}
								}
							}
						}
					}
				}
			}
			else{
				cout << "Error : func is invalid!" << endl;
			}
		}
		count =0;
		show_info(p, pm, num_page, aid, pid, func, num_process);
	}
	cout << "fault = " << page_falut << endl;
	return 0;
}
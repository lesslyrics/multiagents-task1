# Calculate the mean value for the multiagent system

## Task description
Given a graph of agents possessing a certain number, calculate the mean value for this set using agents communications. The graph of agents is not fully connected. 


## Solution

Solution is implemented using the JADE framework and based on the local voting protocol:
 * https://www.researchgate.net/publication/271427886_Local_voting_protocol_in_decentralized_load_balancing_problem_with_switched_topology_noise_and_delays

 * https://www.ipme.ru/ipme/labs/ccs/alf/ARC12AmelinaFr.pdf

## Complexity

N – number of agents

M – number of connections between agents

D – agents-connections graph depth

MSG – number of messages sending operations


**Memory: O(N)**

**MSG: 2\*M\*(D+1)**

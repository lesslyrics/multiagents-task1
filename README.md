# Calculate the mean value for the multiagent system

## Task description
Given a graph of agents possessing a certain number, calculate the mean value for this set using agents communications. The graph of agents is not fully connected. 

### Additional requirements
Each agent receives noisy measurements from their neighbors (also could be delayed). The links between agents can switch from their active state to inactive and backward. However, on average, the time-varying communication graph must be strongly connected.

## Solution

Solution is implemented using the JADE framework and based on the local voting protocol:
 * https://www.researchgate.net/publication/271427886_Local_voting_protocol_in_decentralized_load_balancing_problem_with_switched_topology_noise_and_delays

 * https://www.ipme.ru/ipme/labs/ccs/alf/ARC12AmelinaFr.pdf

The idea of the algorithm is that an agent makes changes to his value based on the values of his neighbours (agents with whom it is able to communicate). I have chosen this algorithm because the idea itself is pretty native and it seems to have performance potential.  

## Complexity

N – number of agents

M – number of connections between agents

D – agents-connections graph depth

MSG – number of messages sending operations


**Memory: O(N)**

**MSG: 2\*M\*(D+1)**

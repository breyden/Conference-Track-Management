-create a graph of the specified verteses each with a list of its edges (adjecency list from second year assignment)
-read the  AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7 and add the appropriate edges.
note an edge has weight which is the distance between two towns and two nodes referenced as an integer

1. To calculate the distance between towns:
-get the starting town and its adj list 
- check if the next town exists and if so continue with the following town until you reach the destination or the is no route 
- dont forget to accumulate the distance along the route.

2. The number of trips starting at one town and ending at one town with a maximum of n stops
- get the towns linked to the from town
- recursive (moving forward) check if its the destination ensuring  you have not exceeded the number of max towns to pass by for a given route
public class Main2 {
    public static void main(String[] args) {
  
        Graph graph= new Graph("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
        
        System.out.println("Output #2: " + graph.displayDistance("ABC"));
        System.out.println("Output #2: " + graph.displayDistance("AD"));
        System.out.println("Output #3: " + graph.displayDistance("ADC"));
        System.out.println("Output #4: " + graph.displayDistance("AEBCD"));
        System.out.println("Output #5: " + graph.displayDistance("AED"));
        System.out.println("Output #6: " + graph.calculateTripsCount("C", "C", t -> t <= 3, 3));
        System.out.println("Output #7: " + graph.calculateTripsCount("A", "C", t -> t == 4, 4));
        System.out.println("Output #8: " + graph.calculateShortestPath("A", "C"));
        System.out.println("Output #9: " + graph.calculateShortestPath("B", "B"));
        System.out.println("Output #10: " + graph.calculateRoutesCount("C", "C", 30));
     
        
       
    }
    

}
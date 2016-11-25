import java.util.*;

public class EllysRoomAssignmentsDiv2 {

  public double getProbability(String[] ratings) {
    String s = "";
    for (String i : ratings) s += i;
    String [] sp = s.split(" ");
    List<Integer> r = new ArrayList<>();
    for (String i : sp)
      r.add(Integer.parseInt(i));
    
    int ellyScore = r.get(0);
    // sorting in reversed order
    Collections.sort(r, (a, b) -> b - a);
    
    //Elly has the highest score
    if (r.get(0) == ellyScore) return 1;
    
    int numRooms = (int)Math.ceil(r.size() / 20.);
    // everyone end up in the same room
    if (numRooms == 1) return 1;
    
    int ellyBatch = (r.indexOf(ellyScore)) / numRooms;
    //Participants in the first batch are all assigned to different rooms
    if (ellyBatch == 0) return 0;
    
    return 1. / numRooms;
  }

}

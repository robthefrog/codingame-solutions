import java.util.*;

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        
        if (in.hasNextLine()) {
            in.nextLine();
        }
        
        // Parse suspects
        List<Suspect> suspects = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String line = in.nextLine();

            // Get suspect name
            String[]split = line.split(":");
            String name = split[0];

            // Get suspect location
            String location = split[1].contains(",") ? 
                split[1].substring(split[1].indexOf("in the") + 7, split[1].indexOf(",")) : 
                split[1].substring(split[1].indexOf("in the") + 7, split[1].indexOf("with"))
                .trim();
            
            // Get suspect's associations
            List<String> with = new ArrayList<>();
            if (!split[1].contains(",")) {
                split = split[1].split("with");
                with = Arrays.asList(split[1].substring(0, split[1].length() - 1).trim().split(" and "));
            }

            suspects.add(new Suspect(name, location, with));
        }

        in.close();

        // Resolve who done it
        for(Suspect suspect : suspects) {
            // Detect filthy liars (Saying they're alone when they weren't according to others sharing the same location)
            // Increment kill rank for suspect if they are lying
            if (suspect.isAlone()) {
                suspects.stream()
                    .filter(x -> 
                        !x.getName().equals(suspect.getName()) &&
                        x.getLocation().equals(suspect.getLocation())
                    )
                    .findFirst()
                    .ifPresent(x -> suspect.incrementKillerRank());
            }

            // Mark off truthful people
            // Decrement kill rank for suspect if other's can vouch for their location
            for(String with : suspect.getWith()) {
                if (suspect.getName().equals(with)) {
                    continue;
                } else {
                    Suspect withSuspect = suspects.stream()
                        .filter(x -> x.getName().equals(with))
                        .findFirst()
                        .get();
                    if (withSuspect.getLocation().equals(suspect.getLocation())){
                        withSuspect.decrementKillerRank();
                    }
                }
            }
        }

        Suspect killer = Collections.max(suspects, Comparator.comparing(x -> x.getKillerRank()));

        System.out.println(killer.getKillerRank() == 0 ? "It was me!" : killer.getName() + " did it!");
    }

    public static class Suspect {
        private int killerRank; // The suspect with the largest killer rank is the killer
        private String name;
        private String location;
        private List<String> with; // Who suspect says they were with
        private boolean alone;

        public Suspect(String name, String location, List<String> with) {
            this.name = name;
            this.location = location;
            this.with = with;
            this.alone = with.size() > 0 ? false : true;
            this.killerRank = with.size();
        }

        public String getName() {
            return name;
        }

        public String getLocation() {
            return location;
        }

        public boolean isAlone() {
            return alone;
        }

        public List<String> getWith() {
            return with;
        }

        public void decrementKillerRank() {
            killerRank--;
        }

        public void incrementKillerRank() {
            killerRank++;
        }

        public int getKillerRank() {
            return killerRank;
        }
    }
}

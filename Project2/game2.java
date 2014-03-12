



import java.util.Scanner;




public class game2 {

    //
    // Public
    //

    // Globals
    public static final int MAX_LOC = 9;    // Total number of rooms/locations we have in the game.
    public static final int MAX_ITEM = 10;    // Total number of items we have in the game.
    public static int currentLoc = 0;        // Player starts in loc 0.
    public static String command;               // What the player types as he or she plays the game.
    public static boolean stillPlaying = true; // Controls the game loop.
    public static Loc[] locs;           // An uninitialized array of type Locale. See init() for initialization.
    public static Item[] items;           // An uninitialized array of type Item. See init() for initialization.
    public static int[][]  nav;                 // An uninitialized array of type int int.
    public static int moves = 0;                // Counter of the player's moves.
    public static int score = 0;                // Tracker of the player's score.
    public static List0 magicItems  = new List0(); //An uninitialized linked list.
    public static int myHealth = 100;            //Player starts with 100 health points.
    public static int bossHealth = 150;            //Enemy Boss starts with 150 health points.
    public static int peas = 14;         //Player starts with 14 peas. Peas are used to buy magic items.
    public static boolean peaGenerator = false; //Player does not start with Magick Item Pea Generator.
    public static int strength = 15;    //Player starts with 25 strength. That's as many push-ups as he can do.
    //For Riddle Man
    public static String[] riddles;   //An uninitialized array of riddles of type String.
    public static String[] answers;   //An uninitialized array of answers of type String.
    public static int riddleCount=0; //An int counter to record what riddles the player have heard

    public static void main(String[] args) {

        // Set starting locale, if it was provided as a command line parameter.
        if (args.length > 0) {
            try {
                int startLocation = Integer.parseInt(args[0]);
                // Check that the passed-in value for startLocation is within the range of actual locations.
                if ( startLocation >= 0 && startLocation <= MAX_LOC) {
                    currentLoc = startLocation;
                } else {
                    System.out.println("WARNING: passed-in starting location (" + args[0] + ") is out of range.");
                }
            } catch(NumberFormatException ex) {
                System.out.println("WARNING: Invalid command line arg: " + args[0]);

            }
        }


        // Get the game started.
        init();
        System.out.println("WELCOME TO SUPER MARKET. \nYou blink back to reality."
                +" The rumbles of shopping carts and hordes of shoppers bring "
                +"back your awareness.\nYou are in a super-market. You put your hand"
                +" in your pocket to find 14 peas, god knows why.\nOh yeah, you needed"
                +" to get milk and eggs. Okay sounds like an easy enough plan."
                +"\n"+ "\n"+  locs[currentLoc].getText());



        // Game Loop
        while (stillPlaying) {
            getCommand();
            commandReader();
        }
    }

    //
    // Private
    //

    private static void init() {
        // Initialize any uninitialized globals.
        command = new String();
        stillPlaying = true;


        // Set up the loc instances of the Loc class.
        Loc loc0 = new Loc(0);
        loc0.setName("Lobby");
        loc0.setDescrip("You are in the LOBBY of the Super-market. "
                + "There are a row of CARTS near you and "
                + "a stand of shopping BROCHUREs next to that.");
        loc0.setDirections("North-Magick Shoppe, East-Cash Register");
        loc0.setHasVisited(true);


        Loc loc1 = new Loc(1);
        loc1.setName("Magick Shoppe");
        loc1.setDescrip("You have entered the Magick Shoppe. The"
                +" Magick Shoppe only accepts tender in the form"
                + " of peas. " +
                "\nTo view all" +
                " the magick items that are for sale, type BROWSE.");
        loc1.setDirections("East-Aisle 4, South-Lobby");


        Loc loc2 = new Loc(2);
        loc2.setName("Cash Register");
        loc2.setDescrip("You are at the Cash Registers. "
                + "A cute CASHIER GIRL notices you and smiles.");
        loc2.setDirections("North-Aisle 4, East-Bakery, West-Lobby");


        Loc loc3 = new Loc(3);
        loc3.setName("Aisle 4");
        loc3.setDescrip("You are in Aisle 4. In the middle of the aisle," +
                "an odd gnome of man sitting in a grocery cart sees you\n" +
                "and yells 'IF YOU WANTS MY PEAS, YOU'LL HAVE TO ANSWER"
                + " MY RIDDLES!'\nType ASK ME to listen to the crazy guy.");
        loc3.setDirections("North-Meats, East-Aisle 8, South-Cash Register, West-Magick Shoppe");


        Loc loc4 = new Loc(4);
        loc4.setName("Meats");
        loc4.setDescrip("You are in the Meat Section. It is ominously quiet"
                + " and you can't see anyone else near you in the store.\n"
                + "The only thing you notice is a cool breeze coming from"
                +" a crack in the FREEZER door and sign that reads\n"
                + "'ENTER if you are ready.'");
        loc4.setDirections("East-Dairy, South-Aisle 4");


        Loc loc5 = new Loc(5);
        loc5.setName("Bakery");
        loc5.setDescrip("You are in the Bakery. You are surrounded on "
                + "all sides by the smell of delicious pastries"
                + " and pies.\nStraight ahead of you,"
                + " a filthy looking BAKER with a "
                + "lazy eye is staring you down.");
        loc5.setDirections("North-Aisle 8, West-Cash Register");


        Loc loc6 = new Loc(6);
        loc6.setName("Aisle 8");
        loc6.setDescrip("You are in Aisle 8. Your eyes lock on to BLACK DRAGON OPAL, shining "
                + "in the middle of the aisle.\nFor the low price of $4.72"
                + " too!");
        loc6.setDirections("North-Dairy, South-Bakery, West-Aisle 4");


        Loc loc7 = new Loc(7);
        loc7.setName("Dairy");
        loc7.setDescrip("You are in the Dairy Section. Next to the myriad "
                + "of fancy cheeses, \nthere are the milk and eggs you've been "
                + "looking for. Oddly enough, there seems to be a gallon of "
                + "GLOWING MILK \nand a carton of NEON EGGS in the middle of"
                + "the shelf.");
        loc7.setDirections("South-Aisle 8, West-Meats");


        Freezer loc8 = new Freezer(8);
        loc8.setName("FREEZER");
        loc8.setDescrip("THE FREEZER!\nYou are face by an horrifying abomination: THE "
                + "BROCCOLI BEHEMOTH. Nothing to do now but fight!\nTo fight with "
                + "grocery items, type USE ITEM_NAME.\nTo fight with you fists, type"
                + " PUNCH.");


        // Set up the location array.
        locs = new Loc[MAX_LOC];
        //initializes all locations in Location Array
        locs[0]=loc0;
        locs[1]=loc1;
        locs[2]=loc2;
        locs[3]=loc3;
        locs[4]=loc4;
        locs[5]=loc5;
        locs[6]=loc6;
        locs[7]=loc7;
        locs[8]=loc8;


        // Set up the navigation matrix for locs
         nav = new int[][] {
         /* N   S   E   W */
         /* 0   1   2   3 */
         {  1, -1,  2, -1 }, //loc0 Lobby
         { -1,  0,  3, -1 }, //loc1 Magick Shoppe
         {  3, -1,  5,  0 }, //loc2 Cash Registers
         {  4,  2,  6,  1 }, //loc3 Aisle 4
         { -1,  3,  7, -1 }, //loc4 Meats (cannot move in Freezer without an ENTER Command)
         {  6, -1, -1,  2 }, //loc5 Bakery
         {  7,  5, -1,  3 }, //loc6 Aisle 8
         { -1,  6, -1,  4 }, //loc7 Dairy
         { -1,  4, -1,  -1 },//loc8 Freezer
    };
        createMagicItems();//initializes MagicItem list.

        //Sets up the item instances of the Item class
        Item item0 = new Item(0);
        item0.setName("Cart");
        item0.setDescrip("You now have something to put your groceries in! Ho0ray!");
        item0.setTaken(false);

        Item item1 = new Item(1);
        item1.setName("Brochure");
        item1.setDescrip("You skim the shopping brochure. Welcome to the SUPER-Market"
                + " blah blah blah... \nThis week's special is discount GRAPES!"
                + " blah blah blah \nAnd our SUPER item of the day is a rare one of a"
                + "kind BLACK DRAGON OPAL for in Aisle 8!\n"
                +"Dont miss out on this KILLER deal!");
        item1.setTaken(false);

        Item item2 = new Item(2);
        item2.setName("Glowing Milk");
        item2.setDescrip("Hmmmm kinda weird this milk is glowing. Maybe it has a extra "
                +"calcium or something.");
        item2.setTaken(false);

        Item item3 = new Item(3);
        item3.setName("Neon Eggs");
        item3.setDescrip("I might regret this decision when making breakfast later.");
        item3.setTaken(false);

        Item item4 = new Item(4);
        item4.setName("Black Dragon Opal");
        item4.setDescrip("W0o0o0o0o0o0o I cant believe this rare amorphous mineraloid is only 5 bucks!");
        item4.setTaken(false);

        Item item5 = new Item(5);
        item5.setName("Baker");
        item5.setDescrip("The Baker hocks a loogie at your feet.\nBAKER- Are you here to defeat HIM?"
                +"\nRespond with:(YES or WHO)");
        item5.setTaken(false);

        Item item6 = new Item(6);
        item6.setName("Cashier Girl");
        item6.setDescrip("+25 points!");
        item6.setTaken(false);


        Item item8 = new Item(8);
        item8.setName("Discount Grapes");
        item8.setDescrip("A bunch of grapes for 82 cents. Whadda deal!");
        item8.setTaken(false);


        // Set up the location array.
        items = new Item[MAX_ITEM];
        //initializes all locations in Location Array
        items[0]=item0;//
        items[1]=item1;
        items[2]=item2;
        items[3]=item3;
        items[4]=item4;
        items[5]=item5;
        items[6]=item6;
        //item[7] was deleted
        items[8]=item8;

        //Builds riddles as Strings for Riddle Array
        String rid0 = "What has roots as nobody sees,\n" +
                "Is taller than trees,\n" +
                "Up, up it goes,\n" +
                "And yet never grows?";
        String rid1 = "Voiceless it cries,\n" +
                "Wingless flutters,\n" +
                "Toothless bites,\n" +
                "Mouthless mutters.";
        String rid2 = "It cannot be seen, cannot be felt,\n" +
                "Cannot be heard, cannot be smelt.\n" +
                "It lies behind stars and under hills,\n" +
                "And empty holes it fills.\n" +
                "It comes out first and follows after,\n" +
                "Ends life, kills laughter.";
        String rid3 = "Alive without breath,\n" +
                "As cold as death;\n" +
                "Never thirsty, ever drinking,\n" +
                "All in mail never clinking.";
        String rid4 = "This thing all things devours;\n" +
                "Birds, beasts, trees, flowers;\n" +
                "Gnaws iron, bites steel;\n" +
                "Grinds hard stones to meal;\n" +
                "Slays king, ruins town,\n" +
                "And beats mountain down.";

        //Sets up riddles array
        riddles=new String[5];
        //initializes all riddle of type String in Riddle Array
        riddles[0]=rid0;
        riddles[1]=rid1;
        riddles[2]=rid2;
        riddles[3]=rid3;
        riddles[4]=rid4;

        //Sets up answers array
        answers=new String[5];
        //initializes all answers of type String in Answers Array
        answers[0]="mountain";
        answers[1]="wind";
        answers[2]="dark";
        answers[3]="fish";
        answers[4]="time";
    }

     //Scans input to get command and displays specific game units when necessary
    private static void getCommand() {
        if(moves==0){
            System.out.print("\n[" + moves + " moves " + score+" points]"     //starting location
                              +"\n["+ peas + " peas, "+strength+" strength, "
                    +myHealth + " health]\n\n");
        }else if (currentLoc == 8){
            System.out.print("\n[" + moves + " moves " + score + " points]\n" + // Battle Scene
                "["+ peas + " peas, "+strength+" strength, "+myHealth + " health]"+
                "\n[Achievement Ratio " + score/moves +"]\n[Boss Health= "+ bossHealth+"]\n\n" );
        }else if (command.equalsIgnoreCase("ask me")){                         //Riddle Move
            System.out.print("\nWhat is it?" );
        }else {
           System.out.print("\n[" + moves + " moves " + score + " points]\n" +//All other moves
                            "["+ peas + " peas, "+strength+" strength, "+myHealth + " health]"+
                            "\n[Achievement Ratio " + score/moves +"]\n\n" );}

        Scanner inputReader = new Scanner(System.in);
        command = inputReader.nextLine();  // command is global.
    }

    //helper function to the player's current score
    private static void addScore(int points){
        score = score+points;
    }

    //shoots specified game actions depending on player's input command
    private static void commandReader() {
        int randomMult = (int) ((Math.random()*11)-5);//generates an int between -5 and 5,for randomness when fighting
        if (        command.equalsIgnoreCase("north") || command.equalsIgnoreCase("n") ) {//moves player north
            move(0);
        } else if ( command.equalsIgnoreCase("south") || command.equalsIgnoreCase("s") ) {//moves player south
            move(1);
        } else if ( command.equalsIgnoreCase("east")  || command.equalsIgnoreCase("e") ) {//moves player east
            move(2);
        } else if ( command.equalsIgnoreCase("west")  || command.equalsIgnoreCase("w") ) {//moves player est
            move(3);
        } else if ( command.equalsIgnoreCase("quit")  || command.equalsIgnoreCase("q")) {//brings up quit options
            quit();
        } else if ( command.equalsIgnoreCase("yes i quit")) {//confirms quit and ends game
            gameover();
        } else if ( command.equalsIgnoreCase("shop on")) {//denies quit and continues playing
            System.out.println("Phewwww. Still need to get those milk and eggs.");
        } else if ( command.equalsIgnoreCase("help")  || command.equalsIgnoreCase("h")) {//brings up help menu
            help();
        } else if ( command.equalsIgnoreCase("map")  || command.equalsIgnoreCase("m")) {//displays game map in ASCII
            map();
        } else if ( command.equalsIgnoreCase("i")  || command.equalsIgnoreCase("inventory")) {//displays current inv.
            inv();
        } else if ( command.equalsIgnoreCase("l")  || command.equalsIgnoreCase("look")) {//redisplays location's descrip
            System.out.println(locs[currentLoc].getDescrip());
        } else if ( command.equalsIgnoreCase("take cart") ) {//takes Item Cart
            if (currentLoc!=0){
                System.out.println("Not in this room!");
            }else if (items[0].getTaken()){
                System.out.println("Already Taken");
            } else {
                System.out.println("CART TAKEN. \n" + items[0].getDescrip());
                items[0].setTaken(true);
                addScore(5);}
    } else if ( command.equalsIgnoreCase("take brochure") ) {//takes Item brochure
        if (currentLoc!=0){
            System.out.println("Not in this room!");
        }else if (items[1].getTaken()){
            System.out.println("Already Taken");
        } else {
            System.out.println("BROCHURE TAKEN. \n" + items[1].getDescrip());
            items[1].setTaken(true);
            addScore(5);
        }
    } else if ( command.equalsIgnoreCase("take glowing milk") ||command.equalsIgnoreCase("take milk")  ) {//takes Item
            if (currentLoc!=7){                                                                         //glowing milk
                System.out.println("Not in this room!");
            }else if (items[2].getTaken()){
                System.out.println("Already Taken");
            }else if (!items[0].getTaken()){
                System.out.println("Nothing to put it in");
            } else {
                System.out.println("GLOWING MILK TAKEN. \n" + items[2].getDescrip());
                items[2].setTaken(true);
                addScore(5);
            }
        } else if ( command.equalsIgnoreCase("take neon eggs") ||command.equalsIgnoreCase("take eggs")){// takes Item
            if (currentLoc!=7){                                                                         //neon eggs
                System.out.println("Not in this room!");
            }else if (items[3].getTaken()){
                System.out.println("Already Taken");
            }else if (!items[0].getTaken()){
                System.out.println("Nothing to put it in");
            } else {
                System.out.println("NEON EGGS TAKEN. \n" + items[3].getDescrip());
                items[3].setTaken(true);
                addScore(5);
            }
        } else if ( command.equalsIgnoreCase("take black dragon opal") ||command.equalsIgnoreCase("take opal") ) {
            if (currentLoc!=6){                                                          //takes Item black dragon opal
                System.out.println("Not in this room!");
            }else if (items[4].getTaken()){
                System.out.println("Already Taken");
            }else if (!items[0].getTaken()){
                System.out.println("Nothing to put it in");
            } else {
                System.out.println("BLACK DRAGON OPAL TAKEN. \n" + items[4].getDescrip());
                items[4].setTaken(true);
                addScore(5);
            }
        } else if ( command.equalsIgnoreCase("take grapes")  || command.equalsIgnoreCase("take discount grapes")) {
            if (currentLoc!=1){                                                                    //takes Item grapes
                System.out.println("Not in this room!");
            }else if (items[8].getTaken()){
                System.out.println("Already Taken");
            }else if (!items[0].getTaken()){
                System.out.println("Nothing to put it in");
            } else {
                System.out.println("GRAPES TAKEN. \n" + items[8].getDescrip());
                System.out.println("AND you find 7 peas under the bag of grapes!");
                items[8].setTaken(true);
                addScore(5);
                peas=peas+7;
            }
        } else if ( command.equalsIgnoreCase("ask me" )){   //asks gnome-guy to tell player a riddle
            if (currentLoc!=3){
                System.out.println("Not in this room!");
            } else {
                riddleTeller();
            }
        }else if (command.equalsIgnoreCase(answers[riddleCount])){  //when riddle is correctly answered
            System.out.println("Errr. You are a clever fellow..." +
                    "\n+5 PEAS");
            peas=peas+5;
            riddleCount= riddleCount +1;

    }else if ( command.equalsIgnoreCase("talk to baker") || command.equalsIgnoreCase("talk baker") ) {//talks to baker
            if (currentLoc!=5){
                System.out.println("Not in this room!");
            }else if (items[5].getTaken()){
                System.out.println("ALREADY TALKED TO");
            } else {
                System.out.println(items[5].getDescrip());
                items[5].setTaken(true);
            }
        }else if ( command.equalsIgnoreCase("who") ) {          //when talking to baker, questions him
            if (currentLoc!=5){
                System.out.println("Not in this room!");
            }else{
                System.out.println("The Baker chops off your head with a "+
                        "machete for questioning him. Never Question Bakers.");
            addScore(-100);
            gameover();

            }
        }else if ( command.equalsIgnoreCase("yes") ) {      //when talking to baker, answer him
            if (currentLoc!=5){
                System.out.println("Not in this room!");
            }else{
                System.out.println("You will need the Black Dragon Opal. Also do not "
                        +"forget to get your milk and eggs.");
                addScore(10);
            }
        }else if ( command.equalsIgnoreCase("smile back") || command.equalsIgnoreCase("smile at cashier") ||
                   command.equalsIgnoreCase("smile at cashier girl") ) {                    //smiles at cashier girl
            if (currentLoc!=2){
                System.out.println("Not in this room!");
            }else if (items[6].getTaken()){
                System.out.println("ALREADY SMILED AT");
            } else {
                System.out.println("SMILE SUCCESSFUL\n" +items[6].getDescrip());
                items[6].setTaken(true);
                addScore(25);
            }
        }else if (command.equalsIgnoreCase("browse")  ) {       //displays magick items in item shoppe
                if (currentLoc!=1){
                    System.out.println("Not in this room!");
                } else {
                    System.out.println(magicItems.toString());
                }
        }else if ( command.equalsIgnoreCase("enter")||( command.equalsIgnoreCase("enter freezer"))) {
            if (currentLoc!=4){                                     //enters subclass of loc Freezer
                System.out.println("Wrong room!");
            } else {
                currentLoc= 8;
                System.out.println(locs[8].getDescrip());
            }
        }else if ( command.equalsIgnoreCase("buy 10" ) ) {    //purchases magick Item Shin Guards of Hope
            int cost10 = 9;
            if (currentLoc!=1){
                System.out.println("Not in this room!");
            }else if (peas<cost10){
                System.out.println("Not enough peas...");
            }else{
                System.out.println("You have purchased the SHIN GUARDS OF HOPE! +20 Health");
                peas=peas-cost10;
                myHealth= myHealth+20;
            }
        }else if ( command.equalsIgnoreCase("buy 11" ) ) {   //purchases magick Item Splendid Sling Shot
            int cost11 = 11;
            if (currentLoc!=1){
                System.out.println("Not in this room!");
            }else if (peas<cost11){
                System.out.println("Not enough peas...");
            }else{
                System.out.println("You have purchased the SPLENDID SLING SHOT! +5 Strength");
                peas=peas-cost11;
                strength= strength+5;
            }
        }else if ( command.equalsIgnoreCase("buy 12" ) ) {   //purchases magick Item Pocket Pea Generator
            int cost12 = 4;
            if (currentLoc!=1){
                System.out.println("Not in this room!");
            }else if (peas<cost12){
                System.out.println("Not enough peas...");
            }else{
                System.out.println("You have purchased the POCKET PEA GENERATOR! " +
                        "You will generate 1 pea per new room you walk in");
                peas=peas-cost12;
                peaGenerator=true;
            }
        }else if (command.equalsIgnoreCase("punch" ) ) {      //attacks enemy boss with a punch
            if (currentLoc!=8){
                System.out.println("Not in this room!");
            }else{
                int playerAttack= strength+randomMult;
                int bossAttack= 20+randomMult;
                System.out.println("POW! "+ playerAttack + " damage!");
                bossHealth= bossHealth-playerAttack;
                myHealth= myHealth- bossAttack;
                System.out.println("BOOM! The Brocolli Behemoth counters! " +bossAttack+" damage!");
                if (bossHealth<1){
                    System.out.println("YOU SAVED THE SUPER-MARKET FROM UTTER DESTRUCTION!\n YOU WIN");
                    addScore(100);
                    gameover();
                }else if (myHealth<1){
                    System.out.print("Dead.\n");
                    gameover();}
            }
    }else if(command.equalsIgnoreCase("use glowing milk") ||command.equalsIgnoreCase("use milk")){
        if (currentLoc!=8&&items[2].taken){                                     //attacks enemy boss with milk Item
            System.out.println("Not in this room!");
        }else{
            int playerAttack= 30;
            int bossAttack= 20+randomMult;
            System.out.println("SLOSH! You throw the gallon of glowing milk straight at his vegi-face,\n"
                    +"leaving him wet and discouraged!"+ playerAttack + " damage!");
            bossHealth= bossHealth-playerAttack;
            myHealth= myHealth- bossAttack;
            System.out.println("SLAM! The Broccolli Behemoth counters! " +bossAttack+" damage!");
            if (bossHealth<1){
                System.out.println("YOU SAVED THE SUPER-MARKET FROM UTTER DESTRUCTION!\n YOU WIN");
                addScore(100);
                gameover();
            }else if (myHealth<1){
                System.out.print("Dead.\n");
                gameover();}
        }
    } else if ( command.equalsIgnoreCase("use neon eggs") ||command.equalsIgnoreCase("use eggs") ) {
        if (currentLoc!=8&&items[3].taken){                                         //attacks enemy boss with egg Item
            System.out.println("Not in this room!");
        }else{
        int playerAttack= 30;
        int bossAttack= 20+randomMult;
        System.out.println("KAPOWIE! You snipe all 12 neon eggs at him,"
                +"stunnin him and hurting his broccolli pride!"+ playerAttack + " damage!");
        bossHealth= bossHealth-playerAttack;
        myHealth= myHealth- bossAttack;
        System.out.println("SMACK! The Broccolli Behemoth counters! " +bossAttack+" damage!");
        if (bossHealth<1){
        System.out.println("YOU SAVED THE SUPER-MARKET FROM UTTER DESTRUCTION!\n YOU WIN");
        addScore(100);
        gameover();
        }else if (myHealth<1){
                    System.out.print("Dead.\n");
                    gameover();
                }
        }
    } else if ( command.equalsIgnoreCase("use black dragon opal") ||command.equalsIgnoreCase("use opal") ) {
        if (currentLoc!=8&&items[4].taken){                                      //attacks enemy boss with opal Item
            System.out.println("Not in this room!");
        }else{
            int playerAttack= 50;
            int bossAttack= 20+randomMult;
            System.out.println("WSSSHHHHH! A ray of light shrines from the opal straight to the heavens."
                    +"From the roof cracks a beam of lightening from Zeus himself.\nOw...-" +
                    "The Broccoli Behemoth sighs"+ playerAttack + " damage!");
            bossHealth= bossHealth-playerAttack;
            myHealth= myHealth- bossAttack;
            System.out.println("KICK! The Broccoli Behemoth counters! " +bossAttack+" damage!");
            if (bossHealth<1){
                System.out.println("YOU SAVED THE SUPER-MARKET FROM UTTER DESTRUCTION!\n YOU WIN");
                addScore(100);
                gameover();
            }else if (myHealth<1){
                System.out.print("Dead.\n");
                gameover(); }}
        }
        else {System.out.println("what?");} //if input command is not recognized
    }

    //if Pocket Pea Generator is bought, adds peas for each new room a player enters
    private static void pocketPea(){
    if(peaGenerator&&!locs[currentLoc].getPeaVisited()){
        peas=peas+1;
        locs[currentLoc].setPeaVisited(true);
        }
    }

    //displays specific riddle from Riddle Array
    private static void riddleTeller(){
        if (riddleCount>=riddles.length )
        {System.out.println("I have no more riddles.");}
        else{
            System.out.println(riddles[riddleCount]);
    }}

    //uses direction matrix to move player throughout grid and display new loc's text
    private static void move(int dir){
        int newloc = nav[currentLoc][dir];
        if (newloc<0){
            System.out.println("You cannot go that way.");
        }else {
            currentLoc = newloc;
            moves=moves+1;
            pocketPea();
            }
            if(!locs[currentLoc].getHasVisited()){
                System.out.println(locs[currentLoc].getText());
                addScore(5);
                locs[currentLoc].setHasVisited(true);
            }else{
                System.out.println(locs[currentLoc].getName()+"\n"+ locs[currentLoc].getDirections());}
        }


    //displays game's help instructions
    private static void help() {
        System.out.println("HELP INSTRUCTIONS:\nSUPER-Market is a text-based adventure game that relies on"
                +" players' text input to navigate through the game field. For certain commands, such as "
                +"directional movement, help and quit, the player can click option buttons. For all other "
                +"commands, the text input box is required.\nA List of Functional Text Input Commands:\n"
                +"TAKE [item name]: The most common command; which is used to retrieve take-able items.\n"
                +"TALK TO [title of person]: The command used to engage in coversation with a person.\n"
                +"SMILE AT [title of person]: Used in one specific and appropriate instance in the game.\n"
                +"RING [item name]: Used in one specific and appropriate instance in the game.\n"
                +"NORTH or N: To move north.\n"
                +"SOUTH or S: To move south.\n"
                +"EAST or E: To move east.\n"
                +"WEST or W: To move west.\n"
                +"QUIT or Q: To quit the game.\n"
                +"HELP or H: To view these help instructions.\n"
                +"LOOK or L: To get the description of the room.\n"
                +"INVENTORY or I: To view all the current items you have attained and people you have talked to.\n"
                +"\nAll attainable items or interactive people in the game are displayed in all CAPITAL LETTERS. "
                +"All other functional user's commands are explained as options within certain instences during "
                +"game play.\n\nGL HF");
    }

    //Confirmation screen when player inputs 'quit'
    private static void quit() {
        System.out.println("Are you sure you want to leave the SUPER-Market? \n (YES I QUIT or SHOP ON)");
    }


    private static void gameover() {
        stillPlaying = false;
        System.out.println("GAME OVER.");
        System.out.println("Final Score: " + score/moves);
    }

    //displays information about the Items the player has taken
    private static void inv(){
            System.out.println("Inventory:");
            for (int i=0;i<items.length;i++){
                if (items[i].getTaken()){
               System.out.println(items[i].toString()+"\n");}}
    }

    //displays ASCII game of map
    private static void map() {
        System.out.println(
                "---------------------------%%%%%%%%%%%%%-------------------------------\n"+
                "---------------------------%%%DAS MAP%%%-------------------------------\n"+
                "---------------------------%%%%%%%%%%%%%-------------------------------\n"+
                "-----------------------------------------------------------------------\n"+
                "---------------------------*************-------------------------------\n"+
                "---------------------------*-----------*-------------------------------\n"+
                "---------------------------*--FREEZER--*-------------------------------\n"+
                "---------------------------*-----------*-------------------------------\n"+
                "---------------------------*************-------*************-----------\n"+
                "---------------------------*-----------*-------*-----------*-----------\n"+
                "---------------------------*---MEATS---*#######*---DAIRY---*-----------\n"+
                "---------------------------*-----------*-------*-----------*-----------\n"+
                "---------------------------*************-------*************-----------\n"+
                "---------------------------------#-------------------#-----------------\n"+
                "---------------------------------#-------------------#-----------------\n"+
                "---------------------------------#-------------------#-----------------\n"+
                "-------*************-------*************-------*************-----------\n"+
                "-------*--MAGICK---*-------*-----------*-------*-----------*-----------\n"+
                "-------*--SHOPPE---*#######*--AISLE-4--*#######*--AISLE-8--*-----------\n"+
                "-------*-----------*-------*-----------*-------*-----------*-----------\n"+
                "-------*************-------*************-------*************-----------\n"+
                "-------------#-------------------#-------------------#-----------------\n"+
                "-------------#-------------------#-------------------#-----------------\n"+
                "-------------#-------------------#-------------------#-----------------\n"+
                "-------*************-------*************-------*************-----------\n"+
                "-------*-----------*-------*---CASH----*-------*-----------*-----------\n"+
                "-------*---LOBBY---*#######*--REGISTER-*#######*--BAKERY---*-----------\n"+
                "-------*-----------*-------*-----------*-------*-----------*-----------\n"+
                "-------*************-------*************-------*************-----------\n"+
                "-----------------------------------------------------------------------\n"+
                "-----------------------------------------------------------------------\n");

    }

    // Create the list manager for our magic items.
public static void createMagicItems(){

    magicItems.setName("Magick Items");
    magicItems.setDesc("These are the magick items. \nTo buy items, type BUY ITEM_NUMBER. EX: BUY 1");
    magicItems.setHead(null);

    // Create some magic items and put them in the list.
    Item i1 = new Item(10);
    i1.setName("Shin Guards of Hope");
    i1.setDescrip("+20 Health");
    i1.setCost(9);

    Item i2 = new Item(11);
    i2.setName("Splendid Sling Shot");
    i2.setDescrip("+5 Strength");
    i2.setCost(11);

    Item i3 = new Item(12);
    i3.setName("Pocket Pea Generator");
    i3.setDescrip("Generates (1) Pea per new room you walk in");
    i3.setCost(4);

    // Link it all up.
    magicItems.setHead(i1);
    i1.setNext(i2);
    i2.setNext(i3);
    i3.setNext(null);

}

}


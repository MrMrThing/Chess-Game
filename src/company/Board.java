package company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Board extends JPanel {

    //Attributes of the board
    private BufferedImage BKnight;
    private BufferedImage BBishop;
    private BufferedImage BKing;
    private BufferedImage BPawn;
    private BufferedImage BQueen;
    private BufferedImage BRook;

    private BufferedImage WKnight;
    private BufferedImage WBishop;
    private BufferedImage WKing;
    private BufferedImage WPawn;
    private BufferedImage WQueen;
    private BufferedImage WRook;

    Countdown countdown = new Countdown();

    ArrayList<Piece> m_pieces = new ArrayList<>();
    int clicked = 0;
    int size = 100; //size of a square?
    int clickedX = 0;
    int clickedY = 0;
    Piece selected = null;
    Point tempPoint;
    Point drawPoint;
    JFrame frame;
    boolean current_turn_color;
    int turn;
    int turn2;

    Game b_game; //the board is connected to the game
    ArrayList<Piece> menacingPieces = new ArrayList<>();
    Piece menacedKing;



    //Creation of the board
    public Board(JFrame frame){
/*

        JPanel panel1 = new JPanel();
        setBorder(new EmptyBorder(100, 10, 10, 10));
        this.setBackground(new Color(239,223,187));

        Font f2= new Font(Font.SERIF,  Font.PLAIN, 20);

        panel1.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        panel1.setBackground(new Color(239,223,187));

        gbc.gridwidth = GridBagConstraints.REMAINDER;

        JButton welcome= new JButton("Welcome");
        welcome.setBackground(new Color(59,47,47));
        welcome.setForeground(new Color(239,223,187));
        welcome.setFont(f2);

        welcome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game b= new Game(frame);
                frame.getContentPane().add(b);
                setVisible(false);
                b.setVisible(true);
            }
        });

        panel1.add(welcome,gbc);

        this.add(panel1);*/

        this.b_game = new Game();
        current_turn_color = b_game.player.m_color;
        frame.add(Countdown.counterLabel);
        frame.add(Countdown.counterLabel2);
        frame.add(Countdown.scoreCounter);
        frame.add(Countdown.scoreCounter2);
        frame.add(Countdown.eatScore);


        this.frame = frame;

        try{
            BKnight = ImageIO.read(Objects.requireNonNull(getClass().getResource("/BKnight.png")));
            BBishop = ImageIO.read(Objects.requireNonNull(getClass().getResource("/BBishop.png")));
            BKing = ImageIO.read(Objects.requireNonNull(getClass().getResource("/BKing.png")));
            BPawn = ImageIO.read(Objects.requireNonNull(getClass().getResource("/BPawn.png")));
            BQueen = ImageIO.read(Objects.requireNonNull(getClass().getResource("/BQueen.png")));
            BRook = ImageIO.read(Objects.requireNonNull(getClass().getResource("/BRook.png")));

            WKnight = ImageIO.read(Objects.requireNonNull(getClass().getResource("/WKnight.png")));
            WBishop = ImageIO.read(Objects.requireNonNull(getClass().getResource("/WBishop.png")));
            WKing = ImageIO.read(Objects.requireNonNull(getClass().getResource("/WKing.png")));
            WPawn = ImageIO.read(Objects.requireNonNull(getClass().getResource("/WPawn.png")));
            WQueen = ImageIO.read(Objects.requireNonNull(getClass().getResource("/WQueen.png")));
            WRook = ImageIO.read(Objects.requireNonNull(getClass().getResource("/WRook.png")));
        } catch (IOException e){
            e.printStackTrace();
        }

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                m_pieces = b_game.getPieces();
                clickedX = me.getX()/100;
                clickedY = me.getY()/100;
                clicked = (clickedX + 1) + (clickedY + 1)*8 - 8;

                tempPoint = new Point(clickedX,clickedY);
                move();

                frame.repaint();

                if(selected != null){
                    selected.emptyPossiblePositions();
                    selected.UpdatePossiblePositions(b_game);
                }
            }
          });
    }


    public void paint(Graphics g){
        boolean color = false;
        Color white = new Color(239,223,187);
        Color black = new Color(59,47,47);
        Color green = new Color(138,154,91);
        int x = 0;
        int y = 0;
        int tempx = 0;
        int tempy = 0;

        //For loop to draw the board
        for(int i = 1; i < 65; i++){

            drawPoint = new Point (x/100,y/100); //Point for checking in getPossiblePosition

            if(i == clicked && selected != null){ //if if has been clicked, and it is selected
                g.setColor(Color.ORANGE);
                color = !color;
            }

            //Make sure selected is not null
            //and check if the current tile is inside the selecteds getPossiblePosition
            else if (selected != null && selected.contains(drawPoint, selected.getPossiblePositions())){
                //Here we color in green all the positions our piece can take
                g.setColor(green);
                color = !color;
            }
            else if(color){
                g.setColor(white);
                color = false;
            } else{
                g.setColor(black);
                color = true;
            }

            //Drawing each rect 
            g.fillRect(x, y, size, size);
            x = x + size;
            if(i%8 == 0){
                y = y + size;
                x = 0;
                if(color)
                    color = false;
                else
                    color = true;
            }

            //Drawing the pieces
            for(int k = 0; k < m_pieces.size(); k++){

                if(m_pieces.get(k).getPositionX() == tempx && m_pieces.get(k).getPositionY() == tempy){

                    if(m_pieces.get(k).getColor()){
                        if(m_pieces.get(k).toString().contains("Pawn")){
                            g.drawImage(WPawn, tempx*100 + 10, tempy*100 + 10, 75, 75, null);
                        } else if (m_pieces.get(k).toString().contains("King")){
                            g.drawImage(WKing, tempx*100 + 10, tempy*100 + 10, 75, 75, null);
                        } else if (m_pieces.get(k).toString().contains("Knight")){
                            g.drawImage(WKnight, tempx*100 + 10, tempy*100 + 10, 75, 75, null);
                        } else if (m_pieces.get(k).toString().contains("Queen")){
                            g.drawImage(WQueen, tempx*100 + 10, tempy*100 + 10, 75, 75, null);
                        } else if (m_pieces.get(k).toString().contains("Bishop")){
                            g.drawImage(WBishop, tempx*100 + 10, tempy*100 + 10, 75, 75, null);
                        } else if (m_pieces.get(k).toString().contains("Rook")){
                            g.drawImage(WRook, tempx*100 + 10, tempy*100 + 10, 75, 75, null);
                        }
                    } else{
                        if(m_pieces.get(k).toString().contains("Pawn")){
                            g.drawImage(BPawn, tempx*100 + 10, tempy*100 + 10, 75, 75, null);
                        } else if (m_pieces.get(k).toString().contains("King")){
                            g.drawImage(BKing, tempx*100 + 10, tempy*100 + 10, 75, 75, null);
                        } else if (m_pieces.get(k).toString().contains("Knight")){
                            g.drawImage(BKnight, tempx*100 + 10, tempy*100 + 10, 75, 75, null);
                        } else if (m_pieces.get(k).toString().contains("Queen")){
                            g.drawImage(BQueen, tempx*100 + 10, tempy*100 + 10, 75, 75, null);
                        } else if (m_pieces.get(k).toString().contains("Bishop")){
                            g.drawImage(BBishop, tempx*100 + 10, tempy*100 + 10, 75, 75, null);
                        } else if (m_pieces.get(k).toString().contains("Rook")){
                            g.drawImage(BRook, tempx*100 + 10, tempy*100 + 10, 75, 75, null);
                        }
                    }



                }
            }

            if(tempx == 7){ //we use this to draw the board
                tempy++;
                tempx = 0;
            } else{
                tempx++;
            }


        }


    }


    //This method moves the selected piece on the board to the new clicked position if its available
    public void move(){


        if(selected != null){ //if something has been selected

            //First we verify if there is check or checkmate happening

            if(this.isCheck()){ //King is in check
                selected.emptyPossiblePositions();
                //We force selected to be either a savior or the king
                selected = canSomeoneSave();
            }


            //If we are not in check
            if(!this.isCheck()){
                selected.emptyPossiblePositions();
                selected.UpdatePossiblePositions(b_game); //Selected is a piece. We update its possible positions
            }

            System.out.println("size of PP: " + selected.getPossiblePositions().size());
            System.out.println(" piece selected is " +selected);


            System.out.println(selected.getPossiblePositions());

            //If selected is a king, we make sure he can't get in danger
            if(selected.pieceName.contains("King")){
                DontGoInCheck(selected);
            }

            if(selected.beSelected){ //if the piece can be selected because no other move is being forced
                if(selected.contains(tempPoint, selected.possiblePositions)){ //if tempPoint is in PP

                    for(int k = 0; k < m_pieces.size(); k++){

                        if(m_pieces.get(k).getPositionX() == clickedX && m_pieces.get(k).getPositionY() == clickedY){ //we check if a piece is on the selected position the player wants to go to
                            // if player = false, then start timer and stop the other timer. Else reverse.
                            if(!current_turn_color){
                                countdown.timer.start();
                                countdown.timer2.stop();
                                //increment player turns
                                turn++;
                            }else {
                                countdown.timer2.start();
                                countdown.timer.stop();
                                turn2++;
                            }
                            //if player = true and over first turn, then increment with 10 seconds. else reverse.
                            if(current_turn_color && turn>=1){
                                countdown.elapsedTime+=10000;
                            } else if (!current_turn_color && turn2>=1){
                                countdown.elapsedTime2 += 10000;
                            }


                            //System.out.println(m_pieces.get(k).getColor());
                            //System.out.println(selected.getColor());

                            if(m_pieces.get(k).getColor() != selected.getColor()){ //if the color of the piece is different from our knight
                                m_pieces.remove(m_pieces.get(k)); //we delete the piece

                                if(Objects.equals(selected.pieceName, "Pawn")){ //if the piece is a pawn
                                    if(selected.getFirstMove()){ //if it's its first move
                                        //if the future position is 2 squares upward or downward, it was the first move of the pawn

                                        selected.setFirstMove(false); //first move was played, not gonna be available anymore

                                }
                            }
                            // if player eat, get points accordingly to the pieces value
                            if(current_turn_color){
                                countdown.points += m_pieces.get(k).value;
                                // if ai eat, get points accordingly to the pieces value
                            }else {
                                countdown.points2 += m_pieces.get(k).value;
                            }
                            // eat queen = eat king !!!
                            System.out.println(m_pieces.get(k).pieceName);
                            System.out.println(m_pieces.get(k).value);


                                selected.setPosition(clickedX, clickedY); //we move the piece here
                                System.out.println("Hello world");
                                current_turn_color = !current_turn_color;
                                menacingPieces.clear();
                                break;
                            }
                        } else {
                            if(Objects.equals(selected.pieceName, "Pawn")){ //if the piece is a pawn
                                if(selected.getFirstMove()){ //if it's its first move
                                    //if the future position is 2 squares upward or downward, it was the first move of the pawn

                                    selected.setFirstMove(false); //first move was played, not gonna be available anymore

                                }
                            }
                            selected.setPosition(clickedX, clickedY); //we move the knight there

                            if(k + 1 == m_pieces.size()){
                                System.out.println("current turn color: " + current_turn_color);
                                current_turn_color = !current_turn_color;
                                menacingPieces.clear();
                            }
                        }
                    }
                }
            }


            selected = null; //we say that nothing has been selected

        }else{ //if nothing has been selected

            //We check for if something is on that clicked tile,
            //if there is a piece on, set selected to it
            for(int k = 0; k < m_pieces.size(); k++){
                if(m_pieces.get(k).getPositionX() == clickedX && m_pieces.get(k).getPositionY() == clickedY){
                    if(m_pieces.get(k).getColor() == current_turn_color){ //Checking if the selected piece is the right color
                        selected = m_pieces.get(k);
                    }

                }
            }

        }
    }

    //This method is called when king is in danger
    //It verifies if another piece can eat the menacing piece so that the king is no longer in danger
    public Piece canSomeoneSave(){
        Piece savior = null;

        for(Piece p : this.m_pieces){
            if(p.color == menacedKing.color){ //if it's an ally to the king

                //We go through its pp to see if it can eat the menacing piece(s)
                for(int i = 0; i < p.possiblePositions.size(); i++){
                    for(int j = 0; j < menacingPieces.size(); j++){

                        //If the ally can eat the menacing piece, it has to
                        if(p.possiblePositions.get(i).x == this.menacingPieces.get(j).getPositionX() && p.possiblePositions.get(i).y == this.menacingPieces.get(j).getPositionY()){
                            System.out.println("You have to move " + p.pieceName + " to " + this.menacingPieces.get(j).position);
                            savior = p;
                            return savior; //selected has to be savior
                            ///HOW DO I FORCE IT: player can't select someone else
                        }
                    }
                }

                //if the piece isn't a savior or the king
                if(!p.equals(savior) && !p.pieceName.contains("King")){
                    //The piece cannot be selected
                    p.beSelected = false;
                }
            }

        }

        //If there is no savior, only the king can save himself
        if(savior == null){
            DontGoInCheck(this.menacedKing);
            return this.menacedKing;
        }

        return null;
    }

    //This method stops the king from putting itself in danger: it modifies its PP
    public void DontGoInCheck(Piece king){
        ArrayList<Point> deleteFromKing = new ArrayList<>();

        for(Piece p : this.m_pieces) {
            p.UpdatePossiblePositions(b_game); //We update all PP

            if (p.color != king.color) { //if the piece is an enemy of the king

                for (int j = 0; j < p.possiblePositions.size(); j++) { //we go through its PP
                    for(int k = 0; k < king.possiblePositions.size(); k++){
                        //if the piece shares a pp with king, king is in danger, he can't go there
                        if (p.possiblePositions.get(j).x == king.possiblePositions.get(k).x && p.possiblePositions.get(j).y == king.possiblePositions.get(k).y) {
                            System.out.println("There is a pp of the checking menaced by an enemy. WXe delete it from king's pp");
                            deleteFromKing.add(king.possiblePositions.get(k));
                        }
                    }
                }
            }
        }

        //Once we got all the pp to delete
        king.possiblePositions.removeAll(deleteFromKing);

        if(king.possiblePositions.size() == 0){
            if(isCheck()){
                ///King is checkmate, game is over
                if(this.isCheckmate()){
                    b_game.isOver = true;
                }
            }
        }
    }

    public boolean isCheck(){ ///if the king is menaced on its current position
        Point kingPosW = new Point();
        Point kingPosB = new Point();
        Piece kingW = null;
        Piece kingB = null;

        //In this loop we find the kings
        for(Piece p : this.m_pieces){

            if(p.pieceName.contains("King") && p.color){ //If it's a king we keep its position
                kingPosW = p.getPosition();
                kingW = p;
            }
            if(p.pieceName.contains("King") && !p.color){ //If it's a king we keep its position
                kingPosB = p.getPosition();
                kingB = p;
            }
        }

        //In this loop we check if a piece has a King's position in its PP
        for(Piece p : this.m_pieces){
            p.UpdatePossiblePositions(b_game); //We update all PP

            if (p.color) { //if the menacing piece is white
                for(int j = 0; j < p.possiblePositions.size(); j++){
                    //if the piece has a possibility to eat the king
                    if(p.possiblePositions.get(j).x == kingPosB.x && p.possiblePositions.get(j).y == kingPosB.y){
                        System.out.println("Next turn the King can be eaten. Check. Move your King");
                        menacingPieces.add(p);
                        menacedKing = kingB;
                        return true; //king is in check
                    }
                }
            }

            if (!p.color) { //if the menacing piece is black
                for(int j = 0; j < p.possiblePositions.size(); j++){
                    //if the piece has a possibility to eat the king
                    if(p.possiblePositions.get(j).x == kingPosW.x && p.possiblePositions.get(j).y == kingPosW.y){
                        System.out.println("Next turn the King can be eaten. Check. Move your King");
                        menacingPieces.add(p);
                        menacedKing = kingW;
                        return true; //king is in check
                    }
                }
            }

        }
        return false; //in case no opposing piece has a king in check
    }

    //called when king can't move and no one can save him
    public boolean isCheckmate(){
        //if every PP of the king is menaced

        //We verify that: king can't move AND no one can save

        if(this.menacedKing.possiblePositions.isEmpty()){ //if king can't move

            for(Piece m : this.menacingPieces){ //for every menacing piece

                //We check if an ally of the king can eat it
                for(Piece p : this.m_pieces){
                    if(p.color == this.menacedKing.color){ //if ally to the king
                        for(int i = 0; i < p.possiblePositions.size(); i++){ //we go through pp
                            if(p.possiblePositions.get(i) == m.position){ //if p can go eat m
                                return false; //not a checkmate
                            }
                        }
                    }
                }
            }
            return true; //game is over, no piece can eat to save the king
        }
        return false; //this king can save himself
    }

   //This method manages the rounds and turns
   //It also will manage the different situations of the game ending
   public void playGame(Game g) { ///WHERE TO CALL IT? take care when all pieces move well

        while (!g.isOver) { //while the game isn't over
            g.m_round++;

            if (g.m_round % 2 == 0) { //if round is even, player2 is playing
                g.player2.m_turn = true;

                while (g.player2.m_turn) {
                    //wait for mouse event
                    addMouseListener(new MouseAdapter() {
                        public void mousePressed(MouseEvent me) {
                            m_pieces = b_game.getPieces();
                            clickedX = me.getX() / 100;
                            clickedY = me.getY() / 100;
                            clicked = (clickedX + 1) + (clickedY + 1) * 8 - 8;

                            tempPoint = new Point(clickedX, clickedY);
                            move();

                            frame.repaint();
                        }
                    });
                }

            } else if (g.m_round % 2 != 0) { //if round is odd, player1 is playing
                g.player.m_turn = true;

                while (g.player.m_turn) {
                    //wait for mouse event
                    addMouseListener(new MouseAdapter() {
                        public void mousePressed(MouseEvent me) {
                            m_pieces = b_game.getPieces();
                            clickedX = me.getX() / 100;
                            clickedY = me.getY() / 100;
                            clicked = (clickedX + 1) + (clickedY + 1) * 8 - 8;

                            tempPoint = new Point(clickedX, clickedY);
                            move();

                            frame.repaint();
                        }
                    });
                }
            }
        }
   }
}

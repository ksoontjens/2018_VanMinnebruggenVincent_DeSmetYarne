package hellotvxlet;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Collections;
import javax.tv.xlet.*;
import org.bluray.ui.event.HRcEvent;
import org.davic.resources.ResourceClient;
import org.davic.resources.ResourceProxy;
import org.dvb.event.EventManager;
import org.dvb.event.UserEvent;
import org.dvb.event.UserEventListener;
import org.dvb.event.UserEventRepository;
import org.havi.ui.HBackgroundConfigTemplate;
import org.havi.ui.HBackgroundDevice;
import org.havi.ui.HBackgroundImage;
import org.havi.ui.HScene;
import org.havi.ui.HSceneFactory;
import org.havi.ui.HScreen;
import org.havi.ui.HStaticText;
import org.havi.ui.HStillImageBackgroundConfiguration;
import org.havi.ui.HTextButton;
import org.havi.ui.HVisible;
import org.havi.ui.event.HBackgroundImageEvent;
import org.havi.ui.event.HBackgroundImageListener;


public class HelloTVXlet implements Xlet, ResourceClient, UserEventListener, HBackgroundImageListener {
    private HStaticText triedText;
    private HStaticText scoreText;
    private HStaticText winText;

    private int huidig = 1;
    private int pressedEnter = 0;
    private int firstCard = 0;
    private int secondCard = 0;
    private int youTried = 0;
    private int score = 0;
    private boolean cardsCollected1 = false, cardsCollected2 = false, cardsCollected3 = false, cardsCollected4 = false, cardsCollected5 = false, cardsCollected6 = false, cardsCollected7 = false, cardsCollected8 = false;
    private String selectedCard = "back1";
    
    private HScene scene = HSceneFactory.getInstance().getDefaultHScene();
    
    private HScreen screen;
    private HBackgroundDevice bgDevice;
    private HBackgroundConfigTemplate bgTemplate;
    private HStillImageBackgroundConfiguration bgConfiguration;
    private HBackgroundImage agrondimg1=new HBackgroundImage("kaartmat.jpg");

    ImageComponent back1 = new ImageComponent("Back_Card.png",5,5);
    ImageComponent back2 = new ImageComponent("Back_Card.png",5,5);
    ImageComponent back3 = new ImageComponent("Back_Card.png",5,5);
    ImageComponent back4 = new ImageComponent("Back_Card.png",5,5);
    ImageComponent back5 = new ImageComponent("Back_Card.png",5,5);
    ImageComponent back6 = new ImageComponent("Back_Card.png",5,5);
    ImageComponent back7 = new ImageComponent("Back_Card.png",5,5);
    ImageComponent back8 = new ImageComponent("Back_Card.png",5,5);
    ImageComponent back9 = new ImageComponent("Back_Card.png",5,5);
    ImageComponent back10 = new ImageComponent("Back_Card.png",5,5);
    ImageComponent back11 = new ImageComponent("Back_Card.png",5,5);
    ImageComponent back12 = new ImageComponent("Back_Card.png",5,5);
    ImageComponent back13 = new ImageComponent("Back_Card.png",5,5);
    ImageComponent back14 = new ImageComponent("Back_Card.png",5,5);
    ImageComponent back15 = new ImageComponent("Back_Card.png",5,5);
    ImageComponent back16 = new ImageComponent("Back_Card.png",5,5);
    ImageComponent border = new ImageComponent ("Border.png",5,5);
    
    public HelloTVXlet() {
        
    }

    public void initXlet(XletContext context) throws XletStateChangeException {
        screen = HScreen.getDefaultHScreen();
        bgDevice = screen.getDefaultHBackgroundDevice();
        if (bgDevice.reserveDevice(this)) { //niet oplossen met bolletje !!! (bovenaan interface toevoegen)
            System.out.println("BackgroundImage device has been reserved");
        }
        else {
            System.out.println("BackgroundImage device cannot be reseerved");
        }
        
        bgTemplate=new HBackgroundConfigTemplate();
        bgTemplate.setPreference(HBackgroundConfigTemplate.STILL_IMAGE, HBackgroundConfigTemplate.REQUIRED);
        bgConfiguration=(HStillImageBackgroundConfiguration) bgDevice.getBestConfiguration(bgTemplate);
        try {
            bgDevice.setBackgroundConfiguration(bgConfiguration); //surround statement with...
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        agrondimg1.load(this);
        
        DrawCards();

        //Repository
        UserEventRepository repository = new UserEventRepository("Voorbeeld");

        //Events toevoegen
        repository.addAllArrowKeys();
        repository.addKey(HRcEvent.VK_SPACE);
        repository.addKey(HRcEvent.VK_ENTER);

        //EventManager
        EventManager.getInstance().addUserEventListener(this,repository);
        
        triedText = new HStaticText("Times tried: ", 450,400,300,400); // x,y,w,h
        triedText.setVerticalAlignment(HStaticText.VALIGN_TOP);
        scene.add(triedText);

        scoreText = new HStaticText("Score: ", 450,460,300,400); // x,y,w,h
        scoreText.setVerticalAlignment(HStaticText.VALIGN_TOP);
        scene.add(scoreText);
        
        winText = new HStaticText("", 450,200,300,400); // x,y,w,h
        winText.setVerticalAlignment(HStaticText.VALIGN_TOP);
        scene.add(winText);
        
        scene.validate();
        scene.setVisible(true);   
        System.out.println("selectedCard = " + selectedCard);
    }
    
    public void imageLoaded(HBackgroundImageEvent e) {
        try {
            bgConfiguration.displayImage(agrondimg1);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("FOUT: er is een probleem met het weergeven van de image");
        }
    }
    
    public void imageLoadFailed(HBackgroundImageEvent e) {
        System.out.println("image is niet geladen");
    }
    
    public boolean requestRelease(ResourceProxy proxy, Object requestData) {
        return false;
    }
    
    public void startXlet() {
    }
    public void pauseXlet() {
    }
    public void destroyXlet(boolean unconditional) {
    }

    public void userEventReceived(UserEvent e) {
        if(e.getType() == KeyEvent.KEY_PRESSED){
            System.out.println("Pushed Button");
            switch(e.getCode()){
                case HRcEvent.VK_UP:
                    System.out.println("VK_UP is PRESSED");
                    huidig -= 4; 
                    if (huidig==-3)huidig=13;
                    if (huidig==-2)huidig=14;
                    if (huidig==-1)huidig=15;
                    if (huidig==0)huidig=16;
                    System.out.println(huidig);
                    break;
                case HRcEvent.VK_DOWN:
                    System.out.println("VK_DOWN is PRESSED");
                    huidig += 4;
                    if (huidig==17)huidig=1;
                    if (huidig==18)huidig=2;
                    if (huidig==19)huidig=3;
                    if (huidig==20)huidig=4;
                    System.out.println(huidig);
                    break;
                case HRcEvent.VK_LEFT:
                    System.out.println("VK_LEFT is PRESSED");
                    huidig--;
                    if (huidig<1)huidig=16; 
                    System.out.println(huidig);
                    break;
                case HRcEvent.VK_RIGHT:
                    System.out.println("VK_RIGHT is PRESSED");
                    huidig++;
                    if (huidig>16)huidig=1; 
                    System.out.println(huidig);
                    break;
                case HRcEvent.VK_SPACE:
                    System.out.println("VK_SPACE is PRESSED");
                    break;
                case HRcEvent.VK_ENTER:
                    EnterPressed(huidig);
                    System.out.println("VK_ENTER is PRESSED");
                    break;
            }
        }
        selectedCard = new String("back" + huidig);
        System.out.println("selectedCard = " + selectedCard);
        //border.setBounds(selectedCard.getBounds());
        switch(huidig) {
            case 1: 
                border.setBounds(back1.getBounds());
                break;
            case 2: 
                border.setBounds(back2.getBounds());
                break;
            case 3: 
                border.setBounds(back3.getBounds());
                break;
            case 4: 
                border.setBounds(back4.getBounds());
                break;
            case 5: 
                border.setBounds(back5.getBounds());
                break;
            case 6: 
                border.setBounds(back6.getBounds());
                break;
            case 7: 
                border.setBounds(back7.getBounds());
                break;
            case 8: 
                border.setBounds(back8.getBounds());
                break;
            case 9: 
                border.setBounds(back9.getBounds());
                break;
            case 10: 
                border.setBounds(back10.getBounds());
                break;
            case 11: 
                border.setBounds(back11.getBounds());
                break;
            case 12: 
                border.setBounds(back12.getBounds());
                break;
            case 13: 
                border.setBounds(back13.getBounds());
                break;
            case 14: 
                border.setBounds(back14.getBounds());
                break;
            case 15: 
                border.setBounds(back15.getBounds());
                break;
            case 16: 
                border.setBounds(back16.getBounds());
                break;
        }
    }
    
    
    public void DrawCards(){
        back1.setBounds(40, 20, 80, 120);
        back2.setBounds(160, 20, 80, 120);
        back3.setBounds(280, 20, 80, 120);
        back4.setBounds(400, 20, 80, 120);
        back5.setBounds(40, 160, 80, 120);
        back6.setBounds(160, 160, 80, 120);
        back7.setBounds(280, 160, 80, 120);
        back8.setBounds(400, 160, 80, 120);
        back9.setBounds(40, 300, 80, 120);
        back10.setBounds(160, 300, 80, 120);
        back11.setBounds(280, 300, 80, 120);
        back12.setBounds(400, 300, 80, 120);
        back13.setBounds(40, 440, 80, 120);
        back14.setBounds(160, 440, 80, 120);
        back15.setBounds(280, 440, 80, 120);
        back16.setBounds(400, 440, 80, 120);
        border.setBounds(40, 20, 80, 120);
        scene.add(back1);
        scene.add(back2);
        scene.add(back3);
        scene.add(back4);
        scene.add(back5);
        scene.add(back6);
        scene.add(back7);
        scene.add(back8);
        scene.add(back9);
        scene.add(back10);
        scene.add(back11);
        scene.add(back12);
        scene.add(back13);
        scene.add(back14);
        scene.add(back15);
        scene.add(back16);
        scene.add(border);
        
        ImageComponent king1 = new ImageComponent("King_Club.png",5,5);
        king1.setBounds(280, 440, 80, 120);
        scene.add(king1);
        ImageComponent king2 = new ImageComponent("King_Diamond.png",5,5);
        king2.setBounds(400, 300, 80, 120);
        scene.add(king2);
        ImageComponent king3 = new ImageComponent("King_Spade.png",5,5);
        king3.setBounds(280, 20, 80, 120);
        scene.add(king3);
        ImageComponent king4 = new ImageComponent("King_Heart.png",5,5);
        king4.setBounds(160, 440, 80, 120);
        scene.add(king4); 
        ImageComponent king5 = new ImageComponent("King_Heart.png",5,5);
        king5.setBounds(160, 300, 80, 120);
        scene.add(king5);
        ImageComponent king6 = new ImageComponent("King_Club.png",5,5);
        king6.setBounds(40, 300, 80, 120);
        scene.add(king6);
        ImageComponent king7 = new ImageComponent("King_Diamond.png",5,5);
        king7.setBounds(400, 440, 80, 120);
        scene.add(king7);
        ImageComponent king8 = new ImageComponent("King_Spade.png",5,5);
        king8.setBounds(280, 300, 80, 120);
        scene.add(king8);
        
        
        
        ImageComponent queen1 = new ImageComponent("Queen_Club.png",5,5);
        queen1.setBounds(160, 20, 80, 120);
        scene.add(queen1);
        ImageComponent queen2 = new ImageComponent("Queen_Heart.png",5,5);
        queen2.setBounds(280, 160, 80, 120);
        scene.add(queen2);
        ImageComponent queen3 = new ImageComponent("Queen_Diamond.png",5,5);
        queen3.setBounds(400, 160, 80, 120);
        scene.add(queen3);
        ImageComponent queen4 = new ImageComponent("Queen_Heart.png",5,5);
        queen4.setBounds(160, 160, 80, 120);
        scene.add(queen4);
        ImageComponent queen5 = new ImageComponent("Queen_Spade.png",5,5);
        queen5.setBounds(40, 20, 80, 120);
        scene.add(queen5);
        ImageComponent queen6 = new ImageComponent("Queen_Club.png",5,5);
        queen6.setBounds(400, 20, 80, 120);
        scene.add(queen6);
        ImageComponent queen7 = new ImageComponent("Queen_Spade.png",5,5);
        queen7.setBounds(40, 160, 80, 120);
        scene.add(queen7);
        ImageComponent queen8 = new ImageComponent("Queen_Diamond.png",5,5);
        queen8.setBounds(40, 440, 80, 120);
        scene.add(queen8);
    }
    
    public void CheckCards(){
        
    }
    
    public void EnterPressed(int pressed){
        switch (pressed){
            case 1:
                back1.setVisible(false);
                pressedEnter++;
                System.out.println("Back1 was REMOVED");
                break;
            case 2:
                back2.setVisible(false);
                pressedEnter++;
                System.out.println("Back2 was REMOVED");
                break;
            case 3:
                back3.setVisible(false);
                pressedEnter++;
                System.out.println("Back3 was REMOVED");
                break;
            case 4:
                back4.setVisible(false);
                pressedEnter++;
                System.out.println("Back4 was REMOVED");
                break;
            case 5:
                back5.setVisible(false);
                pressedEnter++;
                System.out.println("Back5 was REMOVED");
                break;
            case 6:
                back6.setVisible(false);
                pressedEnter++;
                System.out.println("Back6 was REMOVED");
                break;
            case 7:
                back7.setVisible(false);
                pressedEnter++;
                System.out.println("Back7 was REMOVED");
                break;
            case 8:
                back8.setVisible(false);
                pressedEnter++;
                System.out.println("Back8 was REMOVED");
                break;
            case 9:
                back9.setVisible(false);
                pressedEnter++;
                System.out.println("Back9 was REMOVED");
                break;
            case 10:
                back10.setVisible(false);
                pressedEnter++;
                System.out.println("Back10 was REMOVED");
                break;
            case 11:
                back11.setVisible(false);
                pressedEnter++;
                System.out.println("Back11 was REMOVED");
                break;
            case 12:
                back12.setVisible(false);
                pressedEnter++;
                System.out.println("Back12 was REMOVED");
                break;
            case 13:
                back13.setVisible(false);
                pressedEnter++;
                System.out.println("Back13 was REMOVED");
                break;
            case 14:
                back14.setVisible(false);
                pressedEnter++;
                System.out.println("Back14 was REMOVED");
                break;
            case 15:
                back15.setVisible(false);
                pressedEnter++;
                System.out.println("Back15 was REMOVED");
                break;
            case 16:
                back16.setVisible(false);
                pressedEnter++;
                System.out.println("Back16 was REMOVED");
                break;
        }
        
        if (pressedEnter == 1) {
            firstCard = huidig;
        } else if (pressedEnter == 2) {
            secondCard = huidig;
            pressedEnter = 0;
            youTried++;
            String amountOfTries = triedText.getTextContent(HVisible.NORMAL_STATE);
            amountOfTries = "Times tried: " + youTried;
            triedText.setTextContent(amountOfTries, HVisible.NORMAL_STATE); //stelt inhoud in
            triedText.repaint();
            if ((firstCard == 1 && secondCard == 5) || (firstCard == 5 && secondCard == 1)) {
                scene.remove(back1);
                scene.remove(back5);
                if (cardsCollected1 == false) {
                    score++;
                    cardsCollected1 = true;
                }
                String amountOfScore = scoreText.getTextContent(HVisible.NORMAL_STATE);
                amountOfScore = "Score: " + score;
                scoreText.setTextContent(amountOfScore, HVisible.NORMAL_STATE); //stelt inhoud in
                scoreText.repaint();
            }
            else if ((firstCard == 2 && secondCard == 4) || (firstCard == 4 && secondCard == 2)) {
                scene.remove(back2);
                scene.remove(back4);
                if (cardsCollected2 == false) {
                    score++;
                    cardsCollected2 = true;
                }
                String amountOfScore = scoreText.getTextContent(HVisible.NORMAL_STATE);
                amountOfScore = "Score: " + score;
                scoreText.setTextContent(amountOfScore, HVisible.NORMAL_STATE); //stelt inhoud in
                scoreText.repaint();
            }
            else if ((firstCard == 3 && secondCard == 11) || (firstCard == 11 && secondCard == 3)) {
                scene.remove(back3);
                scene.remove(back11);
                if (cardsCollected3 == false) {
                    score++;
                    cardsCollected3 = true;
                }
                String amountOfScore = scoreText.getTextContent(HVisible.NORMAL_STATE);
                amountOfScore = "Score: " + score;
                scoreText.setTextContent(amountOfScore, HVisible.NORMAL_STATE); //stelt inhoud in
                scoreText.repaint();
            }
            else if ((firstCard == 6 && secondCard == 7) || (firstCard == 7 && secondCard == 6)) {
                scene.remove(back6);
                scene.remove(back7);
                if (cardsCollected4 == false) {
                    score++;
                    cardsCollected4 = true;
                }
                String amountOfScore = scoreText.getTextContent(HVisible.NORMAL_STATE);
                amountOfScore = "Score: " + score;
                scoreText.setTextContent(amountOfScore, HVisible.NORMAL_STATE); //stelt inhoud in
                scoreText.repaint();
            }
            else if ((firstCard == 8 && secondCard == 13) || (firstCard == 13 && secondCard == 8)) {
                scene.remove(back8);
                scene.remove(back13);
                if (cardsCollected5 == false) {
                    score++;
                    cardsCollected5 = true;
                }
                String amountOfScore = scoreText.getTextContent(HVisible.NORMAL_STATE);
                amountOfScore = "Score: " + score;
                scoreText.setTextContent(amountOfScore, HVisible.NORMAL_STATE); //stelt inhoud in
                scoreText.repaint();
            }
            else if ((firstCard == 9 && secondCard == 15) || (firstCard == 15 && secondCard == 9)) {
                scene.remove(back9);
                scene.remove(back15);
                if (cardsCollected6 == false) {
                    score++;
                    cardsCollected6 = true;
                }
                String amountOfScore = scoreText.getTextContent(HVisible.NORMAL_STATE);
                amountOfScore = "Score: " + score;
                scoreText.setTextContent(amountOfScore, HVisible.NORMAL_STATE); //stelt inhoud in
                scoreText.repaint();
            }
            else if ((firstCard == 10 && secondCard == 14) || (firstCard == 14 && secondCard == 10)) {
                scene.remove(back10);
                scene.remove(back14);
                if (cardsCollected7 == false) {
                    score++;
                    cardsCollected7 = true;
                }
                String amountOfScore = scoreText.getTextContent(HVisible.NORMAL_STATE);
                amountOfScore = "Score: " + score;
                scoreText.setTextContent(amountOfScore, HVisible.NORMAL_STATE); //stelt inhoud in
                scoreText.repaint();
            }
            else if ((firstCard == 12 && secondCard == 16) || (firstCard == 16 && secondCard == 12)) {
                scene.remove(back12);
                scene.remove(back16);
                if (cardsCollected8 == false) {
                    score++;
                    cardsCollected8 = true;
                }
                String amountOfScore = scoreText.getTextContent(HVisible.NORMAL_STATE);
                amountOfScore = "Score: " + score;
                scoreText.setTextContent(amountOfScore, HVisible.NORMAL_STATE); //stelt inhoud in
                scoreText.repaint();
            }
            else {
                try {
                    Thread.sleep(1500);
                    back1.setVisible(true);
                    back2.setVisible(true);
                    back3.setVisible(true);
                    back4.setVisible(true);
                    back5.setVisible(true);
                    back6.setVisible(true);
                    back7.setVisible(true);
                    back8.setVisible(true);
                    back9.setVisible(true);
                    back10.setVisible(true);
                    back11.setVisible(true);
                    back12.setVisible(true);
                    back13.setVisible(true);
                    back14.setVisible(true);
                    back15.setVisible(true);
                    back16.setVisible(true);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (score == 8) {
                System.out.println("YOU WON ;D");
                String youWon = winText.getTextContent(HVisible.NORMAL_STATE);
                youWon = "YOU WON!!! ;D";
                winText.setTextContent(youWon, HVisible.NORMAL_STATE); //stelt inhoud in
                winText.repaint();
            }
        }
        System.out.print("fistCard = " + firstCard + " secondCard = " + secondCard + "\n");
    }

    public void release(ResourceProxy proxy) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void notifyRelease(ResourceProxy proxy) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

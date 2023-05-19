package org.poker.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player implements IPlayer {

    Card[] cards = new Card[2];
    List<Card> best;
    List<Card> allcomb = new ArrayList<>();
    List<Card> comb = new ArrayList<>();

    private int val;
    private int money = 1000;
    private String name;
    private int position;



    @Override
    public String showName() {
        return this.name;
    }

    @Override
    public void setCards(Card first, Card second) {
        cards[0] = first;
        cards[1] = second;
    }

    @Override
    public String getCards() {
        String first = cards[0].showCard();
        String second = cards[1].showCard();
        return first + " " + second;
    }

    @Override
    public void calculateValue(List<Card> table) {
        List<Card> all = new ArrayList<>(table); // create 7 card list
        all.add(cards[0]);
        all.add(cards[1]);

        this.best = bestComb(all);
        all.clear();
    }

    @Override
    public int getVal() {
        return val;
    }

    @Override
    public void setVal(int val) {
        this.val = val;
    }

    @Override
    public int getMoney() {
        return money;
    }

    @Override
    public void setMoney(int money) {
        this.money = money;
    }


    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public List<Card> bestComb(List<Card> all) {
        List<Card> best = new ArrayList<>();
        List<Card> five = new ArrayList<>();
        combinations(all, 5, 0);
        for (int j = 0; j < 5; j++) {
            five.add(allcomb.get(j));
        }
        int value = evaluate(five);
        setVal(value);
        best.addAll(five);

        for (int i = 0; i < allcomb.size() / 5; i++) {
            for (int j = 0; j < 5; j++) {
                five.add(allcomb.get(i * 5 + j));
            }
            value = evaluate(five);
            if (getVal() < value) {
                best.clear();
                best.addAll(five);
                setVal(value);
            }
            five.clear();
        }
        allcomb.clear();
        return best;
    }

    @Override
    public void combinations(List<Card> all, int k, int offset) {
        if (k == 0) {
            for (int i = 0; i < comb.size(); i++) {
                allcomb.add(comb.get(i));
            }
            return;
        }
        for (int i = offset; i <= all.size() - k; ++i) {
            comb.add(all.get(i));
            combinations(all, k - 1, i + 1);
            comb.remove(comb.size() - 1);
        }
    }

    @Override
    public int evaluate(List<Card> five) {
        five = sortHand(five);
        List<Integer> hands = new ArrayList<>();
        hands.add(straightFlush(five));
        hands.add(fourOfAKind(five));
        hands.add(fullHouse(five));
        hands.add(flush(five));
        hands.add(straight(five));
        hands.add(triple(five));
        hands.add(twoPair(five));
        hands.add(pair(five));
        hands.add(highCard(five));

        return Collections.max(hands);
    }

    @Override
    public int straightFlush(List<Card> five) {
        boolean straight = false;
        boolean flush = false;
        for (int i = 0; i < 4; i++) {
            if (five.get(i).getValue() == five.get(i + 1).getValue() + 1) {
                straight = true;
            } else {
                straight = false;
                break;
            }
        }
        for (int i = 0; i < 4; i++) {
            if (five.get(i).getColor() == five.get(i + 1).getColor()) {
                flush = true;
            } else {
                flush = false;
                break;
            }
        }
        if (straight && flush) {
            return 800 + five.get(0).getValue() + five.get(1).getValue() + five.get(2).getValue() + five.get(3).getValue() + five.get(4).getValue();
        } else {
            return 0;
        }
    }

    @Override
    public int fourOfAKind(List<Card> five) {
        if (five.get(0).getValue() == five.get(1).getValue() && five.get(1).getValue() == five.get(2).getValue() && five.get(2).getValue() == five.get(3).getValue()) {
            return 700 + 4 * five.get(0).getValue() + five.get(4).getValue();
        } else if (five.get(1).getValue() == five.get(2).getValue() && five.get(2).getValue() == five.get(3).getValue() && five.get(3).getValue() == five.get(4).getValue()) {
            return 700 + 4 * five.get(1).getValue() + five.get(0).getValue();
        } else {
            return 0;
        }
    }

    @Override
    public int fullHouse(List<Card> five) {
        if ((five.get(0).getValue() == five.get(1).getValue() && five.get(1).getValue() == five.get(2).getValue()) && (five.get(3).getValue() == five.get(4).getValue())) {
            return 600 + 3 * five.get(0).getValue() + 2 * five.get(4).getValue();
        } else if ((five.get(2).getValue() == five.get(3).getValue() && five.get(3).getValue() == five.get(4).getValue()) && (five.get(0).getValue() == five.get(1).getValue())) {
            return 600 + 2 * five.get(0).getValue() + 3 * five.get(4).getValue();
        } else {
            return 0;
        }
    }

    @Override
    public int flush(List<Card> five) {
        boolean flush = false;
        for (int i = 0; i < 4; i++) {
            if (five.get(i).getColor() == five.get(i + 1).getColor()) {
                flush = true;
            } else {
                flush = false;
                break;
            }
        }
        if (flush) {
            return 500 + five.get(0).getValue() + five.get(1).getValue() + five.get(2).getValue() + five.get(3).getValue() + five.get(4).getValue();
        } else {
            return 0;
        }
    }

    @Override
    public int straight(List<Card> five) {
        boolean straight = true;
        for (int counter = 0; counter < 4; counter++) { // if it's a straight
            if ((five.get(counter).getValue()) == five.get(counter + 1).getValue() + 1) {
                straight = true;
            } else {
                straight = false;
                break;
            }
        }
        if (straight) {
            return 400 + five.get(0).getValue() + five.get(1).getValue() + five.get(2).getValue() + five.get(3).getValue() + five.get(4).getValue();
        } else {
            return 0;
        }
    }

    @Override
    public int triple(List<Card> five) {
        if (five.get(0).getValue() == five.get(1).getValue() && five.get(1).getValue() == five.get(2).getValue()) { // checks for three of a kind
            return 300 + five.get(0).getValue();
        } else if (five.get(1).getValue() == five.get(2).getValue() && five.get(2).getValue() == five.get(3).getValue()) {
            return 300 + five.get(1).getValue();
        } else if (five.get(2).getValue() == five.get(3).getValue() && five.get(3).getValue() == five.get(4).getValue()) {
            return 300 + five.get(2).getValue();
        } else {
            return 0;
        }
    }

    @Override
    public int twoPair(List<Card> five) {
        if ((five.get(0).getValue() == five.get(1).getValue()) && (five.get(2).getValue() == five.get(3).getValue())) {
            return 200 + five.get(0).getValue() + five.get(2).getValue();
        } else if ((five.get(1).getValue() == five.get(2).getValue()) && (five.get(3).getValue() == five.get(4).getValue())) {
            return 200 + five.get(1).getValue() + five.get(3).getValue();
        } else if ((five.get(0).getValue() == five.get(1).getValue()) && (five.get(3).getValue() == five.get(4).getValue())) {
            return 200 + five.get(0).getValue() + five.get(3).getValue();
        } else {
            return 0;
        }
    }

    @Override
    public int pair(List<Card> five) {
        if (five.get(0).getValue() == five.get(1).getValue()) {
            return 100 + five.get(0).getValue();
        } else if (five.get(1).getValue() == five.get(2).getValue()) {
            return 100 + five.get(1).getValue();
        } else if (five.get(2).getValue() == five.get(3).getValue()) {
            return 100 + five.get(2).getValue();
        } else if (five.get(3).getValue() == five.get(4).getValue()) {
            return 100 + five.get(3).getValue();
        } else {
            return 0;
        }
    }

    @Override
    public int highCard(List<Card> five) {
        return five.get(0).getValue();
    }

    @Override
    public List<Card> sortHand(List<Card> five) {
        /*
        sort hand in descending order
        */
        for (int j = 0; j < 4; j++) {
            int first = j;
            for (int i = j + 1; i < 5; i++) {
                if (five.get(i).getValue() > five.get(first).getValue()) {
                    first = i;
                }
            }
            Card c = five.get(j);
            five.set(j, five.get(first));
            five.set(first, c);
        }
        return five;
    }

    @Override
    public String hand() {
        /*
        return what combination player has
        */

        if (val > 800) {
            return "Straight Flush";
        } else if (val > 700) {
            return "Four Of A Kind";
        } else if (val > 600) {
            return "FullHouse";
        } else if (val > 500) {
            return "Flush";
        } else if (val > 400) {
            return "Straight";
        } else if (val > 300) {
            return "Triple";
        } else if (val > 200) {
            return "Two Pairs";
        } else if (val > 100) {
            return "Pair";
        } else {
            return "High Card";
        }
    }

}
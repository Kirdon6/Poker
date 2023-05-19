package org.poker.server;

public class Card implements ICard{

    private Color color;
    private int value;

    public Card() {
    }

    public Card(Color color, int value) {
        this.color = color;
        this.value = value;
    }

    @Override
    public String showCard()
    {
        String first;
        String second;

        switch (color) {
            case clubs:
                first = "C";
                break;
            case hearts:
                first = "H";
                break;
            case diamonds:
                first = "D";
                break;
            case spades:
                first = "S";
                break;
            default:
                first = "A";
        }

        if (value >= 2 && value <= 10) {
            second = Integer.toString(value);
        } else {
            switch (value) {
                case 11:
                    second = "J";
                    break;
                case 12:
                    second =  "Q";
                    break;
                case 13:
                    second = "K";
                    break;
                case 14:
                    second = "A";
                    break;
                default:
                    second="A";
            }
        }
        return first + second;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public void setValue(int value) {
        this.value = value;
    }
}



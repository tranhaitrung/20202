package Bai5;

public class Node {
    private int x;
    private int y;

    public Node(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof Node){
            Node node = (Node) obj;
            if(node.getX() == this.getX() && node.getY() == this.getY()) return true;
        }
        return false;
    }


    @Override
    public int hashCode(){
        return this.getX()*21 + this.getY();
    }
}

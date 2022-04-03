public class PrioQueue {
    Node[] queue;
    int Neff;
    PrioQueue(){
        queue = new Node[9999];
    }
    public void enqueue(Node x){
        //enqueue node ke priority queue
        if (Neff==0){
            queue[Neff] = x;
            Neff++;
        } else {
            for (int i =0;i<Neff;i++){
                if (x.c<=queue[i].c){
                    for (int j=Neff;j>i;j--){
                        queue[j] = queue[j-1];
                    }
                    queue[i] = x;
                    Neff++;
                    break;
                }
            }
        }
    }
    public Node dequeue(){
        //dequeue node dari priority queue
        Node x = queue[0];
        queue[0] = queue[1];
        Neff--;
        for (int i = 1;i<Neff;i++){
            queue[i] = queue[i+1];
        }
        return x;
    }
}

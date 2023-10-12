public class Checking {
    int fee;
    final double intrestRate = 0.01;

    public void setFee(){
        if(Account.getBalance >= 1000){
            this.fee = 0;
        }
        else{
            this.fee = 12;
        }

    }
}

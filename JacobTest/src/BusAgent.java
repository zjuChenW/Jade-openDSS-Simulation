import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.TickerBehaviour;

/**
 * Created by zjuCHENW on 2016/12/21.
 * E-mail: sodared.d3@gmail.com
 * Affiliation: Zhejiang University, Hangzhou, China
 */
public class BusAgent extends Agent {
    private AID[] busAgents;
    
    protected void setup(){
        System.out.println("Hallo! Bus-agent"+getAID().getName()+"is ready.");
        addBehaviour(new TickerBehaviour(this,6000) {
            @Override
            protected void onTick() {
                System.out.println("Trying to read information");
                // TODO: 2016/12/21 取得openDSS接口，获取母线电压信息和电流信息 
                myAgent.addBehaviour(new RequestPerformer());
            }
        });
    }
    private class RequestPerformer extends Behaviour{
        public void action(){
            // TODO: 2016/12/21 根据获取到的母线信息，采取相应的措施 
        }
        public boolean done(){
            return true;
        }
    }
}

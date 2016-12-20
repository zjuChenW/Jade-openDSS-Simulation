import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

/**
 * Created by zjuCHENW on 2016/12/20.
 */
public class Connect2DSS {

    public static void main(String args[]){
        ComThread.InitSTA();
        ActiveXComponent DSSObject = new ActiveXComponent("OpenDSSEngine.DSS");
        ActiveXComponent DSSText = DSSObject.getPropertyAsComponent("Text");
        ActiveXComponent DSSCircuit = DSSObject.getPropertyAsComponent("ActiveCircuit");
        ActiveXComponent DSSSolution = DSSCircuit.getPropertyAsComponent("Solution");

        DSSText.getProperty("Command");
        System.out.println(DSSText.getProperty("Command"));

        DSSText.setProperty("Command","Compile \"D:\\OpenDSS\\IEEETestCases\\123Bus\\IEEE123Master.dss\"");
        DSSText.setProperty("Command","New Capacitor.C1 Bus1=1 Phases=3 kVAR=1200");
        DSSText.setProperty("Command","~ Enabled=false");
        DSSText.setProperty("Command","Line.L1.Bus1 = 5");

        ActiveXComponent loads = DSSCircuit.getPropertyAsComponent("Loads");
        Variant iLoads = loads.getProperty("First");
        System.out.println(iLoads);

    }
}

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import static com.jacob.com.Variant.VariantFloat;

/**
 * Created by zjuCHENW on 2016/12/20.
 * E-mail: sodared.d3@gmail.com
 * Affiliation: Zhejiang University, Hangzhou, China
 */
public class Connect2DSS {

    public static void main(String args[]){
        //获取com接口
        ComThread.InitSTA();
        ActiveXComponent DSSObject = new ActiveXComponent("OpenDSSEngine.DSS");

        //获取openDSS的三个接口Text、Circuit、Solution
        ActiveXComponent DSSText = DSSObject.getPropertyAsComponent("Text");
        ActiveXComponent DSSCircuit = DSSObject.getPropertyAsComponent("ActiveCircuit");
        ActiveXComponent DSSSolution = DSSCircuit.getPropertyAsComponent("Solution");

        //通过设置Text接口下的Command属性，来控制openDSS调用IEEE123算例
        DSSText.setProperty("Command","Compile \"D:\\OpenDSS\\IEEETestCases\\123Bus\\IEEE123Master.dss\"");
        DSSText.setProperty("Command","New Capacitor.C1 Bus1=1 Phases=3 kVAR=1200");
        DSSText.setProperty("Command","~ Enabled=false");
        DSSText.setProperty("Command","Line.L1.Bus1 = 5");

        //输出节点电压为一个csv文件
        DSSText.setProperty("Command","Export Voltages");
        String result = DSSText.getPropertyAsString("Result");
        System.out.println("结果文件输出到了："+result);

        //通过设置Circuit接口下的Loads属性，来修改所有负荷的值到1.2倍
        int iLoads = 0;
        ActiveXComponent loads = DSSCircuit.getPropertyAsComponent("Loads");
        int First = loads.getProperty("First").getInt();
        while(First!=0){
            iLoads = iLoads +1 ;
            double load = loads.getProperty("kW").changeType(VariantFloat).getFloat(); //提取kW属性里的值
            load = load * 1.2;
            String sload = "" + load;
            loads.setProperty("kW",sload);//将每个负荷点的值加到1.2倍
            System.out.println("第"+iLoads+"个负荷点的值为： "+loads.getProperty("kW")+"kW");
            First = loads.getPropertyAsInt("Next");
        }
        System.out.println("结束！");

    }
}

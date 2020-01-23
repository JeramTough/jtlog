package test;

import com.jeramtough.jtlog.jtlogger.Logger;
import com.jeramtough.jtlog.jtlogger.LoggerManager;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * <pre>
 * Created on 2019/12/4 20:08
 * by @author JeramTough
 * </pre>
 */
public class Yhj {

    private static int m = 4;//资源种类数量
    private static int n = 5;//进程个数
    //。如果Available[j]=k,则表示系统中现有j类资源k个尚未分配
    private static int[] Available = new int[m];
    //如果Allocation[i,j]=k,则表示进程i当前已分得j类资源k个
    private static int[][] Allocation = new int[n][m];
    //Need[i,j]=k,则表示进程i还需要j类资源k个才能完成任务
    private static int[][] Need = new int[n][m];
    //Request[j]=k表示当前申请资源的进程Pi申请k个j类资源
    private static int[] Request;


    static {
        //分配种类
        Available[0] = 3;
        Available[1] = 2;
        Available[2] = 5;
        Available[3] = 4;

        //给线程分配资源
        Allocation[0] = new int[]{0, 2, 1, 6};
        Allocation[1] = new int[]{1, 2, 0, 0};
        Allocation[2] = new int[]{0, 0, 1, 2};
        Allocation[3] = new int[]{4, 2, 3, 0};
        Allocation[4] = new int[]{0, 2, 5, 1};

        //线程需要的资源
        Need[0] = new int[]{5, 3, 1, 7};
        Need[1] = new int[]{3, 3, 3, 1};
        Need[2] = new int[]{0, 1, 2, 0};
        Need[3] = new int[]{0, 5, 1, 3};
        Need[4] = new int[]{1, 1, 0, 5};
    }

    public static void main(String[] args) {
        //1.调用printSourseInfo()输出初始资源分配状态
        printSourseInfo();

        while (true) {
            //2.要求用户输入请求资源的进程编号i和请求向量Request
            Request = new int[]{1, 1, 0, 0};

            //3.调用compareArray(int[] a,int[] b)检查用户输入的请求向量Request是否有效
            if (compareArray(Request, Available)) {
                System.out.println("输入的Request【有效】");
            }
            else {
                System.out.println("输入的Request【无效】");
            }

            //4.Request有效则试探分配
            changeData(1, Request);

            //5.调用printSourseInfo()输出试探分配后的资源分配状态
            printSourseInfo();

            //6.进行安全性检查，安全输出安全序列；
            boolean isPassed = check(1);

            //不安全取消试探分配，调用printSourseInfo()输出取消试探分配后的资源分配状态
            if (!isPassed){
                recoverData(1,Request);
                printSourseInfo();
            }

            break;
        }
    }

    //比较两个一维数组的大小，返回值是true代表a[]<=b[]，否则为false
    private static boolean compareArray(int[] a, int[] b) {
        for (int i = 0; i < b.length; i++) {
            if (a[i] > b[i]) {
                return false;
            }
        }
        return true;
    }

    //以矩阵形式输出资源分配情况
    private static void printSourseInfo() {
        System.out.println("进程\t   已分配\t     请求需要    \t   可利用种类");
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                System.out.println(String.format("p[%d]  ，  %s  ， %s  ， %s", i,
                        Arrays.toString(Allocation[i]), Arrays.toString(Need[i]),
                        Arrays.toString(Available)));
            }
            else {
                System.out.println(String.format("p[%d]  ， %s  ， %s  ， %s", i,
                        Arrays.toString(Allocation[i]), Arrays.toString(Need[i]), ""));
            }
        }
    }

    private static void changeData(int thread, int[] Request) {
        for (int i = 0; i < 4; i++) {
            //重新调整系统资源数
            Available[i] -= Request[i];
            //计算各个线程拥有资源
            Allocation[thread][i] += Request[i];
            //重新计算需求
            Need[thread][i] -= Available[i];
        }
    }

    //对线程thread安全性检查
    private static boolean check(int thread) {
        boolean finish[] = new boolean[5];
        int work[] = new int[4];
        int queue[] = new int[5];   //由于存放安全队列
        int k = 0;//安全队列下标
        int j;  //要判断的线程
        int i;
        //是否分配的标志
        for (i = 0; i < 5; i++)
            finish[i] = false;
        j = thread;
        for (i = 0; i < 4; i++) {
            work[i] = Available[i];
        }
        while (j < 5) {
            for (i = 0; i < 4; i++) {
                if (finish[j]) {
                    j++;
                    break;
                }
                else if (Need[j][i] > work[i]) {
                    //System.out.println(need[j][i]+"*"+i+work[i]);
                    j++;
                    break;
                }
                else if (i == 2) {
                    for (int m = 0; m < 4; m++) {
                        work[m] += Allocation[j][m];
                    }
                    finish[j] = true;
                    queue[k] = j;
                    k++;
                    j = 0;   //从最小线程再开始判断
                }
            }
        }

        //判断是否都属于安全状态
        for (int p = 0; p < 5; p++) {
            if (finish[p] = false) {
                System.out.println("系统不安全，资源申请失败");
                return false;
            }
        }
        System.out.println("资源申请成功，安全队列为：");
        for (int q = 0; q < 5; q++) {
            System.out.println(queue[q]);
        }
        return true;
    }

    //安全性检查为通过，分配失败时调用，恢复系统原状
    private static void recoverData(int thread, int[] Request) {
        for (int i = 0; i < 4; i++) {
            //重新调整系统资源数
            Available[i] += Request[i];
            //计算各个线程拥有资源
            Allocation[thread][i] -= Request[i];
            //重新计算需求
            Need[thread][i] += Request[i];
        }
    }

    private void test(){
        try {
            FileReader fileReader=new FileReader("");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

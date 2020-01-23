package test.ncfp.bean;

/**
 * <pre>
 * Created on 2019/12/17 15:34
 * by @author JeramTough
 * </pre>
 */
public class Main {


    public static void main(String[] args) {

    }

    /**
     * 首次适应算法
     */
    public Memory firstAdaptive(Memory memory, int size) {
        int sum = 0;
        //循环内存中所有分区
        for (int i = 0; i < memory.getHoles().size(); i++) {
            sum++;
            //为循环首次适应算法设置最后寻址的下标
            memory.setLastFind(i);
            Hole hole = memory.getHoles().get(i);   //获得对应的分区
            //若此分区空闲且大小大于申请的大小，则申请内存
            if (hole.isFree() && hole.getSize() >= size) {
                System.out.println("查找" + sum + "次");
                return memory.getMemory(size, i, hole);
            }
        }
        System.err.println("OUT OF MEMORY!");
        return memory;
    }


}

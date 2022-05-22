package Live_Dome;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 1.实现 lock接口
 * 2. 写一个静态类 继承AbstractQueuedSynchronizer
 */
public class CreateLock implements Lock {
    /**
     * 3. 重写AQS的方法 只有5个方法可以重写
     * 如果想写一个排它锁 就使用两个方法就行了
     */
    private static class Sync extends AbstractQueuedSynchronizer{
        @Override
        protected boolean tryAcquire(int arg) {  //获取锁
            if(!Thread.currentThread().getName().startsWith("Like-HeBeiWangXiaoZhang")){
                //线程以Like-HeBeiWangXiaoZhang开头
                return false;
            }
            if(compareAndSetState(0,arg)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true; //加锁成功
            }
            return false; //加锁失败
        }


        @Override
        protected boolean tryRelease(int arg) {  //释放锁
            if(getState()==0){   //没有锁
                throw new IllegalMonitorStateException();
            }
            setState(0);  //状态变为0 解锁
            return true;
        }

          Condition getcondition(){
            return new ConditionObject();
        }
    }

     private Sync sync=new Sync();//创建内部类

    @Override
    public void lock() {
        sync.acquire(1);  //模板方法
    }

    @Override
    public boolean tryLock() {

        return sync.tryAcquire(1);
    }
    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1,unit.toNanos(time));
    }
    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }
    @Override
    public void unlock() {
        sync.release(1);
    }
    @Override
    public Condition newCondition() {
        return sync.getcondition();
    }
}

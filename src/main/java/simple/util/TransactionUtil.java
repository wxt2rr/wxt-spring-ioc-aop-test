package simple.util;

public class TransactionUtil {

    public static void open() throws Exception{
        ConnUtil.getInstance().get().setAutoCommit(false);
    }

    public static void rollback() throws Exception{
        ConnUtil.getInstance().get().rollback();
    }

    public static void commit() throws Exception{
        ConnUtil.getInstance().get().commit();
    }

    public static void close() throws Exception{
        ConnUtil.getInstance().get().close();
    }
}

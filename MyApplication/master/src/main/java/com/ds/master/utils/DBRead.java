package com.ds.master.utils;

/**
 * 读取与操作数据库的类
 */
public class DBRead {

    /*private static final String TAG = "DBRead";

    public static File telFile;
    //ctrl + C  抽取成常量
    public static final String DB_PATH = "data/data/com.zxzq.phonebutler";

    {
        //读取数据库  data/data/包名
        File fileDir = new File(DB_PATH);

        //创建目录
        fileDir.mkdir();
        //单击鼠标右键，copy reference复制全路径
        telFile = new File(DB_PATH, "commonnum.db");
        //打印日志
        Log.d(TAG, "telFile的路径是" + DB_PATH);
    }

    //创建一个方法，检测是否存在数据库
    public static boolean isExistDBFile() {
        File toFile = DBRead.telFile;
        //toFile不存在或者toFile的长度小于等于0  都是不合法的
        if (!toFile.exists() || toFile.length() <= 0) {
            return false;
        } else {//否则 是合法的
            return true;
        }
    }

    //创建一个方法，用来读取数据库文件中的classlist表 用arraylist来读
    //需要一个方法来解析数据库里的classlist表中的数据，类的返回值是arraylist类型  arraylist还要指定泛型
    public static ArrayList<TelClassInfo> readTeldbClasslist() {
        //解析具体数据类型
        //arraylist是个集合  是用来放数据的
        ArrayList<TelClassInfo> infos = new ArrayList<>();
        //操作数据库的对象
        SQLiteDatabase db = null;

        //游标，不停移动不停遍历，每遍历一次数据改变一次，用来遍历操作数据库的  表格前边的小三角形
        Cursor cursor = null;
        try {
            db = SQLiteDatabase.openOrCreateDatabase(telFile, null);
            //数据库的对象  rawQuery：挨个遍历
            //第一个：数据库的操作语句  第二个：
            cursor = db.rawQuery("select * from classlist", null);
            if (cursor.moveToFirst()) {
                do {
                    //通过name，拿到name列中所有数据，赋值给string类型变量，通过索引
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    //通过idx，拿到idx列中所有数据  返回给一个int类变量
                    int idx = cursor.getInt(cursor.getColumnIndex("idx"));
                    //通过它来解析出数据  把数据放到arraylist中
                    //String name 和 int idx都是分解数据，classinfo是将数据装起来
                    TelClassInfo classInfo = new TelClassInfo(name, idx);
                    //将分解出来的所有数据装到arraylist中 即infos中
                    infos.add(classInfo);
                } while (cursor.moveToNext());//是否能让cursor移到下一个数据里，如果能，重复执行do
            }
        } catch (Exception e) {
            throw e;//抛异常
        } finally {
            //把刚才所有的东西都关上 结束掉
            try {
                cursor.close();
                db.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.d(TAG, "******一共读取了" + infos.size() + "个数据库的数据");
        }
        //因为是arraylist类型  所以需要return
        return infos;
    }

    //读取table中的表，解析table中的具体内容，根据idx去读取它对应的信息是什么
    public static ArrayList<TelNumberInfo> readTeldbTable(int idx) {
        ArrayList<TelNumberInfo> numberInfo = new ArrayList<>();

        String sql = "select * from table" + idx;//idx是上边readTeldbTable后边括号中要传入的idx
        //遍历
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = SQLiteDatabase.openOrCreateDatabase(tel cursor = db.rawQuery(sql, null);
File, null);

            if (cursor.moveToFirst()) {
                do {
                    //调表中具体的数据
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    String number = cursor.getString(cursor.getColumnIndex("number"));

                    TelNumberInfo telNumberInfo = new TelNumberInfo(name, number);
                    numberInfo.add(telNumberInfo);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
            db.close();
        }
        return numberInfo;
    }*/
}

package com.suntoon.test;

import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.Query;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.shapefile.dbf.DbaseFileHeader;
import org.geotools.data.shapefile.dbf.DbaseFileReader;
import org.geotools.data.shapefile.files.ShpFiles;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.opengis.feature.Property;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectionName desktop
 * @ClassName ReadDbf
 * @Description TODO
 * @Author YueLifeng
 * @Date 2019/5/15 0015下午 4:21
 * @Version 1.0
 */
public class ReadDbf {

    public void readDbfByShp(String path) throws IOException {
        File file = new File(path);
        FileDataStore dataStore = FileDataStoreFinder.getDataStore(file);
        ((ShapefileDataStore) dataStore).setCharset(Charset.forName("GBK"));
        SimpleFeatureSource source = dataStore.getFeatureSource();
        SimpleFeatureType schema = source.getSchema();

        Query query = new Query(schema.getTypeName());
        //query.setMaxFeatures(2);

        FeatureCollection<SimpleFeatureType, SimpleFeature> collection = source.getFeatures(query);
        try (FeatureIterator<SimpleFeature> features = collection.features()) {
            while (features.hasNext()) {
                SimpleFeature feature = features.next();
                System.out.println(feature.getID() + ": ");
                for (Property attribute : feature.getProperties()) {
                    System.out.println("\t" + attribute.getName() + ":" + attribute.getValue());
                }
            }
        }
    }

    public void readDbf(String path) throws Exception {
        FileInputStream fis = new FileInputStream( path );
        DbaseFileReader dbfReader =  new DbaseFileReader(fis.getChannel(),
                false,  Charset.forName("GBK"));

        while ( dbfReader.hasNext() ){
            final Object[] fields = dbfReader.readEntry();

            String field1 = (String) fields[0];
            Integer field2 = (Integer) fields[1];

            System.out.println("DBF field 1 value is: " + field1);
            System.out.println("DBF field 2 value is: " + field2);
        }

        dbfReader.close();
        fis.close();
    }

    public Map<String, Object> dbaseReader(String path) throws Exception {
        Map<String, Object> map = new HashMap<>();
        Object[] columnNames = null;
        List list = new ArrayList<Object[]>();
        DbaseFileReader reader = null;
        try {
            //第三个参数是使用一个charset类型的实例，使用的参数是编码类型
            reader = new DbaseFileReader(new ShpFiles(path), false, Charset.forName("GBK"));
            DbaseFileHeader header = reader.getHeader();
            //返回字段的数量
            int numFields = header.getNumFields();

            columnNames = new Object[numFields];
            for (int i = 0; i < numFields; i++) {
                columnNames[i] = header.getFieldName(i);
            }
            map.put("header", columnNames);

            System.out.println(numFields);
            //迭代读取记录
            while (reader.hasNext())
            {
                list.add(reader.readEntry());
            }
            map.put("data", list);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (reader != null)
            {
                //关闭
                try
                {
                    reader.close();
                } catch (Exception e) {

                }
            }
        }
        return map;
    }

    public static void main(String[] args) throws Exception {
        ReadDbf readDbf = new ReadDbf();
        //readDbf.readDbfByShp("E:\\ArcGISdata\\bou4_4m\\BOUNT_poly.shp");
        //readDbf.readDbf("E:\\ArcGISdata\\bou4_4m\\BOUNT_poly.dbf");
        readDbf.dbaseReader("E:\\ArcGISdata\\bou4_4m\\BOUNT_poly.dbf");
    }
}

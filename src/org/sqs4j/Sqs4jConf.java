package org.sqs4j;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.stream.Format;

import java.io.*;
import java.nio.charset.Charset;

/**
 * Sqs4J�����ļ� User: wstone Date: 2010-7-30 Time: 13:08:46
 */
@Root(name = "Sqs4j")
public class Sqs4jConf {
  @Element
  public String bindAddress = "*"; //������ַ,*��������

  @Element
  public int bindPort = 1218; //�����˿�

  @Element
  public int backlog = 200; //���� backlog ����

  @Element
  public int soTimeout = 60; //HTTP����ĳ�ʱʱ��(��)

  @Element
  public String defaultCharset = "UTF-8"; //ȱʡ�ַ���
  public Charset charsetDefaultCharset = Charset.forName(defaultCharset); //HTTP�ַ���

  @Element(required = false)
  public String dbPath = ""; //���ݿ�Ŀ¼,ȱʡ��:System.getProperty("user.dir", ".") + "/db"

  @Element
  public int syncinterval = 1; //ͬ���������ݵ����̵ļ��ʱ��

  @Element
  public String adminUser = "admin"; //����Ա�û���

  @Element
  public String adminPass = "123456"; //����Ա����

  @Element
  public int jmxPort = 1219; //JMX�����˿�

  @Element(required = false)
  public String auth = ""; //Sqs4j��get,put,view����֤����,Ϊ��ʱ����֤

  public static Sqs4jConf load(String path) throws Exception {
    Persister serializer = new Persister();

    InputStreamReader reader = null;
    try {
      reader = new InputStreamReader(new FileInputStream(path), "UTF-8");
      Sqs4jConf conf = serializer.read(Sqs4jConf.class, reader);
      return conf;
    } finally {
      if (reader != null) {
        try {
          reader.close();
        } catch (IOException ex) {
        }
      }
    }
  }

  public void store(String path) throws Exception {
    Persister serializer = new Persister(new Format(2, "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"));
    OutputStreamWriter writer = null;
    try {
      if (dbPath.equals(System.getProperty("user.dir", ".") + "/db")) {
        dbPath = "";
      }
      writer = new OutputStreamWriter(new FileOutputStream(path), "UTF-8");
      serializer.write(this, writer);
    } finally {
      if (writer != null) {
        try {
          writer.close();
        } catch (IOException ex) {
        }
      }
    }
  }

  @Override
  public String toString() {
    return "Sqs4jConf{" + "bindAddress='" + bindAddress + '\'' + ", bindPort=" + bindPort + ", backlog=" + backlog
        + ", soTimeout=" + soTimeout + ", defaultCharset='" + defaultCharset + '\'' + ", dbPath='" + dbPath + '\''
        + ", syncinterval=" + syncinterval + ", adminUser='" + adminUser + '\'' + ", adminPass='" + adminPass + '\''
        + ", jmxPort='" + jmxPort + '\'' + ", auth='" + auth + '\'' + '}';
  }
}

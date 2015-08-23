package com.tgb.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
* ��Servlet��ͻ��˷���һ��json����Ϊ�˼�㣬���ݲ��Ǵ����ݿ��õġ�
* jqGridĬ���������ص�json�����ʽҪ�����£�
* {"page":"1","total":"2","records":"13",
* "rows":[
*                 {id:"1",cell:["1","polaris","��","polaris@gmail.com","772618379","18329382732","1985-10-2"]},
*                 {id:"2",cell:["2","����","Ů","zhangsan@163.com","272618382","15329382732","1986-10-12"]},
*                 {id:"3",cell:["3","����","Ů","wangwu@yahoo.com","172635372","13329389832","1987-12-21"]},
*                 {id:"4",cell:["4","����","��","zhaoliu@sina.com","372618332","18929343731","1988-09-22"]}
*         ]
* }
* ��Ȼ����js�У�����ͨ��jqGrid��jsonReader�������޸�Ĭ�ϸ�ʽ
* ��ΪĬ�ϵĸ�ʽ��rows������Ҫ��˳���ܱ䣬��ÿ���ֶζ�����ֵ����Ҳ����"")�������
* ��jsonReader�ж���repeatitems : false��������rows�ͱ���ˣ�
* "rows":[
*                 {id:"1",userName:"polaris",gender:" ��",email:"polaris@gmail.com",QQ:"772618379",mobilePhone:"18329382732",birthday:"1985-10-2"]},
*                 {id:"2",userName:"���»�",gender:" ��",email:"xh.xu@163.com",QQ:"272618382",mobilePhone:"15329382732",birthday:"1986-10-12"]},
*                 {id:"3",userName:"����",gender:" Ů",email:"wangwu@yahoo.com",QQ:"172635372",mobilePhone:"13329389832",birthday:"1987-12-21"]},
*                 {id:"4",userName:"����",gender:" Ů",email:"zhaoliu@sina.com",QQ:"372618332",mobilePhone:"18929343731",birthday:"1988-09-22"]}
*         ]
* @author xuxinhua
*
*/
public class JqGridForJSONServlet extends HttpServlet
{
        private static final long serialVersionUID = 132383828833L;

        @Override
        public void doGet(HttpServletRequest req, HttpServletResponse resp)
                        throws ServletException, IOException {
                // ���巵�ص��������ͣ�json��ʹ����json-lib
                JSONObject jsonObj = new JSONObject();
                // ����jqGrid��JSON�����ݸ�ʽҪ���jsonObj��ֵ
                jsonObj.put("page", 1);                // ��ǰҳ
                jsonObj.put("total", 1);        // ��ҳ��
                jsonObj.put("records", 4);        // �ܼ�¼��
                // ����rows���������
                JSONArray rows = new JSONArray();
                // ����4������
                for(int i=0;i<4;i++)
                {
                        // ���һ����¼�Ķ���
                        JSONObject cell = new JSONObject();
                        cell.put("id", i);
                        if(i%2==0)
                        {
                                cell.put("userName", "polaris");
                                cell.put("gender", "Ů");
                        }
                        else
                        {
                                cell.put("userName", "���»�");
                                cell.put("gender", "��");
                        }
                        cell.put("email", "polaris@gmail.com");
                        cell.put("QQ", "772"+i+"1837"+i);
                        cell.put("mobilePhone", "132"+i+"1837"+i+"3"+i);
                        cell.put("birthday", "198"+i+"-10-"+"1"+i);
                        // ���ü�¼����rows��
                        rows.add(cell);
                }
                // ��rows����json������
                jsonObj.put("rows", rows);
                // �Կ���̨��ӡ������Լ���json���������Ƿ���ȷ
                System.out.println("Ҫ���ص�json����\n" + jsonObj.toString());
                // �����ַ�����
                resp.setCharacterEncoding("UTF-8");
                // ����json����ͨ��PrintWriter�����
                resp.getWriter().print(jsonObj);
        }
        @Override
        public void doPost(HttpServletRequest req, HttpServletResponse resp)
                        throws ServletException, IOException {
                doGet(req,resp);
        }
}

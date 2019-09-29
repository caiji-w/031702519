import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.io.FileOutputStream;
//import org.json.simple.JSONArray;
public class Main 
{
		public static void main(String[] args)
	    {
			System.out.println(args[3]+args[4]);
			ArrayList<String> listg;
			Main m=new Main();
			listg=m.getfile(args[3]);
			m.write(listg, args[4]);
	    }
		@SuppressWarnings("finally")
		public ArrayList<String> getfile(String filename)
		{ 
			//filename="D:\\E\\javapra\\1.txt";
			ArrayList<String> listg=new ArrayList<String>();
			try 
	        {

	            BufferedReader in = new BufferedReader(new FileReader(filename));

	            String str=null;

	            while ((str = in.readLine())!= null) 

	            {

	                System.out.println(str);

	                String st=str;

	                listg.add(st);

	            } 

	            in.close();

	        } 

	        catch (IOException e) 

	        {

	            e.printStackTrace();

	        }

			finally

			{

				return listg;

			}

		}

		public void write(ArrayList<String> listg,String filename)

		{

			//filename="D:\\E\\javapra\\2.txt";

			try 

			{

				int l=10;

				int len=listg.size();

				File file=new File(filename);

		        FileOutputStream out=new FileOutputStream(file,true);

		        StringBuffer sb=new StringBuffer();

		        sb.append("[");

		        out.write(sb.toString().getBytes("utf-8"));

		        out.close();

				for(int i=0;i<len;i++) 

				{

					if(listg.get(i).charAt(0)=='1')

					{

						l=8;

					}

					else

					{

						l=10;

					}

					String[] s=transfer(listg.get(i));
					StringBufferDemo(s,l,filename);
					if(i!=len-1)

					{   FileOutputStream out3=new FileOutputStream(file,true);
						StringBuffer a=new StringBuffer();

			        a.append(",\t\n");

			        out3.write(a.toString().getBytes("utf-8"));

			        out3.close();}

				}

				FileOutputStream out2=new FileOutputStream(file,true);

		        StringBuffer ss=new StringBuffer();

		        ss.append("]");

		        out2.write(ss.toString().getBytes("utf-8"));

		        out2.close();

			}

			catch(IOException e)

			{

				System.out.print("wrong");

				e.printStackTrace();

				

			}

		}

		public void StringBufferDemo(String[] s,int l,String filename) throws IOException

	    {

	          File file=new File(filename);

	          FileOutputStream out=new FileOutputStream(file,true);

	          for(int i=0;i<l;i++)

	          {

	        	    StringBuffer sb=new StringBuffer();

	        	    if(i==0)

	        	    {

	        	    	String st="{";

	        	    	sb.append(st);

	        	    }

	        	    /*else if(i>0&&i<3)

	        	    {

	        	    	String st2="\t\t";

	        	    	sb.append(st2);

	        	    }

	        	    else if(i>=3)

	        	    {

	        	    	String st3="\t\t\t";

	        	    	sb.append(st3);

	        	    }*/

	                sb.append(s[i]);

	                out.write(sb.toString().getBytes("utf-8"));

	                //out.write("\t\n".getBytes("utf-8"));

	               // System.out.println(s[i]);

	          }

	          StringBuffer ss=new StringBuffer();

	          ss.append("]}");

	          out.write(ss.toString().getBytes("utf-8"));

	          out.close();

	    }

		ArrayList<Character> list=new ArrayList<Character>();

        public String[] transfer(String str) 

		{

			int len=str.length();

			int i=0;

			String [] s=new String[20];

			for(i=2;i<len;i++)

			{

				char ch=str.charAt(i);

				Character Ch=new Character(ch);

				list.add(Ch);

			}

			s[0]="\"姓名\":\"";

			for(i=0;i<list.size();)//名字

			{

				if(list.get(i)==',')

				{

					list.remove(i);

					break;

				}

				s[0]=s[0]+list.get(i);

				list.remove(i);

			}

			if(i+1==list.size())

			{

				System.out.println("lack of ,");

			}

			s[0]=s[0]+"\",";

			System.out.println(s[0]);

			

			int flag=0;

			s[1]="\"手机\":\"";

			for(i=0;i<list.size();i++)//手机号

			{

				

				if(flag>0&&flag<11&&!isDigit(list.get(i)))

				{

					flag=0;

				}

				char ch=list.get(i);

				if(ch>='0'&&ch<='9')

				{

					flag++;

				}

				if(flag==11)

				{

					for(int j=i-10;j<i+1;j++)

					{

						s[1]=s[1]+list.get(i-10);

						list.remove(i-10);

					}

					break;

				}

			}

			s[1]=s[1]+"\",";

			System.out.println(s[1]);

			s[2]="\"地址\":[";

			//省

			String sflag="";

			sflag=sflag+list.get(0);

			sflag=sflag+list.get(1);

			String f="";

			s[3]="\"";

			for(i=0;i<32;i++)

			{

			    f=p.prov[i][0].substring(0, 2);

				if(cmp(f,sflag)=="")

				{

				    s[3]=s[3]+p.prov[i][0];

				    delete(p.prov[i][0]);

					break;

				}

			}

			s[3]=s[3]+"\",";

			if(i>=32)

			{

			     System.out.println("wrong! province dose not exist");

			     System.exit(1);

			}

			System.out.println(s[3]);

			int pnum=i;//市，自治州

			sflag="";

			sflag=sflag+list.get(0);

			sflag=sflag+list.get(1);

			f="";

			for(i=1;i<p.prov[pnum].length;i++)

			{

			    f=p.prov[pnum][i].substring(0, 2);

				if(cmp(f,sflag)=="")

				{

				    s[4]="\""+p.prov[pnum][i];

				    delete(p.prov[pnum][i]);

					break;

				}

			}

			if(p.prov[pnum].length==i+1)

			{

				if(pnum>=23&&pnum<=26)

				{

					s[4]=p.prov[pnum][0]+"市";

				}

			}

			s[4]=s[4]+"\",";

			System.out.println(s[4]);

			//县级

			f="";

			s[5]="\"";

			for(i=0;i<list.size();i++)

			{

				if(list.get(i)=='县'||list.get(i)=='区')

				{

					for(int j=0;j<=i;j++)

					{

						f=f+list.get(0);

						list.remove(0);

					}

					break;

				}

			}

			s[5]=s[5]+f;

			s[5]=s[5]+"\",";

			System.out.println(s[5]);

			f="";

			s[6]="\"";

			for(i=0;i<list.size();i++)

			{

				if(list.get(i)=='镇'||list.get(i)=='乡'||(list.get(i)=='道'&&i-1>=0&&list.get(i-1)=='街'))

				{

					for(int j=0;j<=i;j++)

					{

						f=f+list.get(0);

						list.remove(0);

					}

					break;

				}

			}

			s[6]=s[6]+f;

			s[6]=s[6]+"\",";

			System.out.println(s[6]);

			

		    if(str.charAt(0)=='2')

		    {   

		    	f="";

				for(i=0;i<list.size();i++)

				{

					if(list.get(i)=='街'||list.get(i)=='路'||list.get(i)=='巷')

					{

						for(int j=0;j<=i;j++)

						{

							f=f+list.get(0);

							list.remove(0);

						}

						break;

					}

				}

				s[7]="\""+f;

				s[7]=s[7]+"\",";

				System.out.println("s[7]="+s[7]);

				f="";

				for(i=0;i<list.size();i++)

				{

					if(list.get(i)=='号')

					{

						for(int j=0;j<=i;j++)

						{

							f=f+list.get(0);

							list.remove(0);

						}

						break;

					}

					

				}

				s[8]="\""+f;	

		        s[8]=s[8]+"\",";		

		        System.out.println("s[8]="+s[8]);

			}

		   

			f="";

			for(;!list.isEmpty();)

			{

				if(list.get(0)!='.')

				{

					f=f+list.get(0);

				}

				list.remove(0);

			}

			

			if(str.charAt(0)=='2')

			{

				s[9]="\""+f;

				s[9]=s[9]+"\"";

			}

			else

			{

				s[7]="\""+f;

				s[7]=s[7]+"\"";

			}

			return s;

		}

		public static boolean isDigit(char ch)

		{

			return (ch>='0'&&ch<='9');

		}

		public static String cmp(String a,String b)

		{

			String re="";

			int len1=a.length();

			int flag=0;

			for(int j=0;j<len1;j++)

			{

				if(a.charAt(j)!=b.charAt(j))

				{

					flag=1;

				}

				if(flag==1)

				{

					re=re+a.charAt(j);

				}

			}

			return re;

		}

		public String delete(String s)

		{

		    int i=0;

		    String str="";

		    int len=s.length();

			for(i=0;i<len;i++)

			{

				if(list.get(0)==s.charAt(i))

				{

					str=str+list.get(0);

					list.remove(0);

				}

				else if(list.get(0)!=s.charAt(i))

				{

					return str;

				}

			}

			return "fail";

		}

}

//["直辖市/省(省级)","直辖市/市(地级)","区/县/县级市(县级)","街道/镇/乡(乡镇级)","详细地址"]

/*地址一定从大到小排序.

省/市级行政区如后缀为“省”/“市”，则有可能省略后缀.

若县级行政区后缀为“县”，则可能缺失整个市级行政区.

除第5条情况外，省市级行政区不会缺失.

县/乡级行政区可能缺失*/

class p

{

	public static String [][] prov=new String[][]

	{

		{"广东省",

			     "广州市","深圳市","珠海市","汕头市","佛山市","韶关市","湛江市","肇庆市",

			     "江门市","茂名市","惠州市","梅州市","汕尾市","河源市","阳江市","清远市",

			     "东莞市","中山市","潮州市","揭阳市","云浮市"},

		{"河北省",

				 "石家庄市", "唐山市","邯郸市","张家口市", "保定市", "秦皇岛市","承德市",

				 "邢台市 ","沧州市", "衡水市 ","廊坊市 ","雄安新区"},

		{"山西省",

				  "太原市","长治市", "大同市","阳泉市","朔州市","临汾市","忻州市","吕梁市",

				  "运城市", "晋中市","晋城市"},

		{"海南省",

				   "三亚市","海口市","三沙市","儋州市","五指山市","文昌市","琼海市","万宁市","东方市"},

		{"四川省",

				  "成都市","绵阳市","自贡市","攀枝花市","泸州市","德阳市","广元市","遂宁市","内江市",

				  "乐山市","资阳市","宜宾市","南充市","达州市","雅安市","广安市","巴中市","眉山市",

				  "阿坝藏族羌族自治州","甘孜藏族自治州","凉山彝族自治州"},

		{"吉林省",

				"长春市","吉林市","四平市","辽源市","通化市","白山市","松原市","白城市"},

		{"辽宁省",

				"沈阳市","大连市","鞍山市","抚顺市","本溪市","丹东市","锦州市","营口市",

				"阜新市","辽阳市","盘锦市","铁岭市","朝阳市","葫芦岛市",},

		{"陕西省",

				"安市","宝鸡市","咸阳市","铜川市","渭南市","延安市","榆林市","汉中市","安康市","商洛市"},

		{"甘肃省",

				  "兰州市","嘉峪关市","金昌市","白银市","天水市","武威市","张掖市","平凉市",

				   "酒泉市","庆阳市","定西市","陇南市 ", "临夏回族自治州","甘南藏族自治州",},

		{"青海省",

				   "西宁市","海东市","德令哈市","格尔木市","玉树市","海西蒙古族藏族自治州","海南藏族自治州",

				   "海北藏族自治州","黄南藏族自治州","果洛藏族自治州","玉树藏族自治州", },//10

		{"云南省",

					"昆明市","曲靖市","玉溪市","丽江市","普洱市","保山市","昭通市","临沧市","自治州是", 

					"德宏傣族景颇族自治州","怒江僳僳族自治州", "迪庆藏族自治州","大理白族自治州","楚雄彝族自治州 ",

					"红河哈尼族彝族自治州","文山壮族苗族自治州"," 西双版纳傣族自治州",},

		{"贵州省",

					"贵阳市","六盘水市","遵义市","安顺市","铜仁市","毕节市","黔南布依族苗族自治州", "黔西南布依族苗族自治州", 

                    "贵州黔东南苗族侗族自治州",},

		{"山东省",

                    "济南市","青岛市","淄博市","枣庄市","东营市","烟台市","潍坊市","济宁市","泰安市",

                    "威海市","日照市","滨州市","德州市","聊城市","临沂市","菏泽市","莱芜市",},

		{"河南省",

			       "郑州市","开封市","洛阳市","平顶山市","焦作市","鹤壁市","新乡市","安阳市","濮阳市", 

			       "许昌市","漯河市","三门峡市 ","南阳市 ","商丘市","信阳市","周口市","驻马店市","济源市", },

		{"江苏省",

			      "南京市","无锡市","徐州市","常州市","苏州市","南通市","连云港市","淮安市","盐城市",

			      "扬州市","镇江市","泰州市","宿迁市",},

		{"浙江省",

			    	 "舟山市","杭州市","嘉兴市","温州市","宁波市","绍兴市","湖州市",

			    	 "丽水市","台州市","金华市","衢州市",},

		{"安徽省",

			    	"合肥市","马鞍山市","淮北市","宿州市","阜阳市","蚌埠市","淮南市","滁州市","六安市",

			    	"巢湖市","芜湖市","亳州市","安庆市","池州市","铜陵市","宣城市","黄山市", },

	    {"江西省",

			    	"南昌市","九江市","景德镇市","萍乡市","新余市","鹰潭市","赣州市","宜春市","上饶市","吉安市","抚州市",},

		{"福建省",

			    	"福州市","厦门市","泉州市","莆田市","漳州市","宁德市","南平市","三明市","龙岩市",},

		{"台湾省",

			    	"台北市","新北市","桃园市","台中市","台南市","高雄市",},//20

		{"湖北省",

			    	"武汉市","黄石市","襄阳市","荆州市","宜昌市","十堰市",

			    	"孝感市","荆门市","鄂州市","黄冈市","咸宁市","随州市",},

		{"湖南省",

			    	"长沙市","株洲市","湘潭市","衡阳市","邵阳市","岳阳市","常德市",

			    	"张家界市","益阳市","娄底市","郴州市","永州市","怀化市","湘西土家族苗族自治州",},

		{"黑龙江省",

			    	"哈尔滨市","齐齐哈尔市","牡丹江市","佳木斯市","大庆市","鸡西市",

			    	"双鸭山市","伊春市","七台河市","鹤岗市","黑河市","绥化市","大兴安岭地区",},

		{"北京",

			    "东城区","西城区","朝阳区","丰台区","石景山区","海淀区","顺义区","通州区","大兴区",

			    "房山区","门头沟区","昌平区","平谷区","密云区","怀柔区","延庆区",},

		{"上海",

			    "黄浦区","徐汇区","长宁区","静安区","普陀区","虹口区","杨浦区","宝山区","闵行区","嘉定区","松江区",

			    "青浦区","奉贤区","金山区","浦东新区","崇明区",},

		{"天津",

			    "和平区","河东区","河西区","南开区","河北区","红桥区","滨海新区","东丽区","西青区","津南区",

			    "北辰区","武清区","宝坻区","宁河区","静海区","蓟州区",},

		{"重庆",

			    "万州区","黔江区","涪陵区","渝中区","大渡口区","江北区","沙坪坝区","九龙坡区","南岸区","北碚区",

			    "渝北区","巴南区","长寿区","江津区","合川区","永川区","南川区","綦江区","大足区","璧山区","铜梁区",

			    "潼南区","荣昌区","开州区","梁平区","武隆区",},

		{"内蒙古自治区",

			             "呼和浩特市","包头市","乌海市","赤峰市","通辽市","鄂尔多斯市","呼伦贝尔市","巴彦淖尔市","乌兰察布市",},

		{"广西壮族自治区",  

			          " 南宁市","柳州市","桂林市","梧州市","北海市","防城港市","钦州市",

			          "贵港市","玉林市","百色市","贺州市","河池市","来宾市","崇左市",},

		{"新疆维吾尔自治区",

			        	 "乌鲁木齐市","克拉玛依市","吐鲁番市","哈密市",

			        	 "阿克苏地区","喀什地区","和田地区","塔城地区","阿勒泰地区",

			        	 "昌吉回族自治州","博尔塔拉蒙古自治州","巴音郭楞蒙古自治州","克孜勒苏柯尔克孜自治州","伊犁哈萨克自治州",},//30

		{"宁夏回族自治区",

			        	"银川市","石嘴山市","吴忠市","固原市","中卫市",},

		{"西藏自治区",

			        	"拉萨市","日喀则市","山南市","林芝市","昌都市","那曲市和阿里地区",},//32

	};

}

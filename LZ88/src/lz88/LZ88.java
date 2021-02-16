package lz88;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class LZ88 {
    public static class con{
        public int index;
        public String added;
        public con(){
            this.index=0;
            this.added="";
        }
    }
    public static class com$dec{
        public static ArrayList<con> lis=new ArrayList();
        public com$dec() {
            
        }   
        public static void decom() throws FileNotFoundException{
            Scanner scanner = new Scanner(new File("newFile.txt"));
            while(scanner.hasNext()){
                con c=new con();
                String tmp=scanner.next();
                c.added = tmp.substring(tmp.length()-1);
                tmp=tmp.substring(0, tmp.length()-1);
                c.index=Integer.parseInt(tmp);
                lis.add(c);
                System.out.println(lis.size());
            }
        }
     public static void print() throws IOException{
         ArrayList<String> li=new ArrayList();
         String str="";
         li.add("");
         FileWriter filew=new FileWriter("CommpresdFile.txt");
        for(int i=0;i<lis.size();i++){
            str="";
            int index=lis.get(i).index;
            str+=li.get(index)+lis.get(i).added;
            filew.write(str);
            System.out.println(lis.get(i).index+" And this Character"+lis.get(i).added);
            li.add(str);
        }
        filew.close();
     }
   }
    public static void main(String[] args) throws IOException{
            String txt="";
            String Compressed="";
            FileWriter filew=new FileWriter("newFile.txt");
           ArrayList<con>list=new ArrayList();
            FileReader fil=new FileReader("file.txt");
            int i;
            while( (i = fil.read()) != -1){
                txt+=(char) i;
            }
            System.out.println(txt);
            ArrayList<String>arr=new ArrayList();
            arr.add("");
            for(i=0;i<txt.length();i++){
                String x="";
                String str="";
                boolean exist=true;
                int index=0;
                x+=txt.charAt(i);
               while(exist==true && i<txt.length()-1) { 
                   if(arr.contains(x)){
                       ++i;
                       index=arr.indexOf(x);
                       x+=txt.charAt(i);
                   }else{
                       Compressed+=x;
                       if(Compressed.length()==txt.length()-1){
                           System.out.println("<"+index+","+txt.charAt(i)+">");
                           filew.write(index+""+txt.charAt(i)+" ");
                           con con1=new con();
                           con1.index=index;
                           con1.added+=txt.charAt(i);
                           list.add(con1);
                       }else{
                            System.out.println("<"+index+","+txt.charAt(i)+">");
                            filew.write((int)index+""+txt.charAt(i)+" ");
                               con con1=new con();
                                con1.index=index;
                                con1.added+=txt.charAt(i);
                                list.add(con1);
                       }
                       exist=false;
                   }   
               }
               if(i == txt.length()-1){
                   System.out.println("<"+index+","+txt.charAt(i)+">");
                   filew.write((int)index+""+txt.charAt(i)+" ");
                   Compressed+=txt.charAt(i);
                    con con1=new con();
                           con1.index=index;
                           con1.added+=txt.charAt(i);
                           list.add(con1);
               }else
                    arr.add(x);
            }
            for(i=0;i<arr.size();i++){
                System.out.println(i+" -> "+arr.get(i));
            }
            for(i=0;i<list.size();i++){
                System.out.println(list.get(i).index+" , "+list.get(i).added);
            }
            com$dec dec=new com$dec();
            filew.close();
            dec.decom();
            dec.print();
      }
    }
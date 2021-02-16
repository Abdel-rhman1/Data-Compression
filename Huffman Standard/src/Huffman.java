import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue; 
import java.util.Scanner; 
import java.util.Comparator; 
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
class HuffmanNode { 
	int data; 
	char c;
	HuffmanNode left; 
	HuffmanNode right; 
}
class MyComparator implements Comparator<HuffmanNode> { 
        @Override
	public int compare(HuffmanNode x, HuffmanNode y) { 
		return x.data - y.data; 
	} 
} 
public class Huffman { 
       public static Map<String,String>map=new HashMap<String,String>();
	public static void printCode(HuffmanNode root, String s) { 
            if (root.left == null&& root.right == null && Character.isLetter(root.c)) { 
                String x="";x+=root.c;
                map.put(x,s); 
                return; 
            }
            printCode(root.left, s +"0"); 
            printCode(root.right, s +"1"); 
        }
        public static Map<String,Integer> proba(String txt){
            Map<String, Integer> geek 
            = new HashMap<String, Integer>(); 
            for(int i=0;i<txt.length();i++){
                String x =""; x+=txt.charAt(i);
                if(geek.get(x)==null){
                    geek.put(x, 1);
                    
                }else{
                    geek.put(x ,(int)geek.get(x)+1);
                }
            }
            return geek;
        }
        public static void decomp(String compfile,Map<String,String> d){
            String y="";
            ArrayList<String> keys =new ArrayList();
            ArrayList<String> values = new ArrayList();
            for (Map.Entry<String, String> m : d.entrySet()) {  
                 keys.add(m.getKey());
                 values.add(m.getValue());
            }
            String [] codes = compfile.split(" ");
            System.out.print("Commpressed String: ");
           for (String code : codes) {
               int index = values.indexOf(code);
               System.out.print(keys.get(index));
           }
            System.out.println("\n");
         }
	public static void main(String[] args) throws IOException 
	{ 
		Scanner s = new Scanner(System.in); 
                String txt="";
		int i=0; 
            try {
                FileReader file=new FileReader("file.txt");
                while( (i = file.read()) != -1){
                    txt+=(char) i;
                }              
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Huffman.class.getName()).log(Level.SEVERE, null, ex);
            }
            PriorityQueue<HuffmanNode> q 
                    = new PriorityQueue<HuffmanNode>(txt.length(), new MyComparator());
            Map<String,Integer> dic=new HashMap<String,Integer>();
            dic=proba(txt);
            for (Map.Entry<String, Integer> m : dic.entrySet()) { 
                HuffmanNode hn = new HuffmanNode();
                hn.c = m.getKey().charAt(0);
                hn.data = m.getValue();
                hn.left =  null; 
                hn.right = null; 
                q.add(hn); 
        } 
        HuffmanNode root = null; 
        while (q.size() > 1) { 
            HuffmanNode x = q.peek(); 
            q.poll(); 
            HuffmanNode y = q.peek(); 
            q.poll(); 
            HuffmanNode f = new HuffmanNode(); 
            f.data = x.data + y.data; 
            f.c = '-'; 
            f.left = x; 
            f.right = y; 
            root = f; 
            q.add(f); 
        }
        printCode(root, "");
         for (Map.Entry<String, String> m : map.entrySet()) { 
             System.out.print(m.getKey()+":");
             System.out.println(m.getValue()); 
        }
        String com="",h="";;
        for(i=0;i<txt.length();i++){
            String x="";x+=txt.charAt(i);
            h+=map.get(x);
            com+=map.get(x)+" "; 
        }
        System.out.println("codes: "+h);
        decomp(com,map);
    } 
}
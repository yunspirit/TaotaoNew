import java.util.*;

public class IsSubsequence {
    private static volatile int a=1;
    public boolean isSubsequence(String s, String t) {
        if(s.length()>t.length()) return false;
        List<Integer> m[]=new List[26];
        for (int i = 0; i <t.length() ; i++) {
            if(m[t.charAt(i)-'a']==null) {
                m[t.charAt(i)-'a']=new LinkedList<Integer>();
            }
            m[t.charAt(i)-'a'].add(i);
        }
        int pre=0;
        for (int i = 0; i < s.length(); i++) {
            int  c=s.charAt(i)-'a';
            if(m[c]==null) return false;
            int index=Collections.binarySearch(m[c],pre);
            if(index<0) index=-index-1;
            if(index==m[c].size()) return  false;
            pre=m[c].get(index)+1;
        }
        return  true;
    }

    public boolean validUtf8(int[] data) {
        int i=0;
        while (i<data.length){
            int num=0;
            if(data[i]>255) return false;
            else if((data[i]&128)==0) num=1;
            else if((data[i]&224)==192) num=2;
            else if((data[i]&240)==224) num=3;
            else if((data[i]&248)==240) num=4;
            else return false;
            for (int j=1;j<num;j++){
                if(i+j>data.length-1) return false;
                if((data[i+j]&192)!=128) return false;
            }
            i=i+num;
        }
        return true;
    }
    public int longestSubstring(String s, int k) {
        int i=0,res=0;
        while (i+k<=s.length()){
            int m[]=new int[26],mask=0,index=0;
            for (int j = i; j <s.length() ; j++) {
                int t=s.charAt(j)-'a';
                ++m[t];
                if(m[t]>=k) mask &=~(1<<t);
                else mask |=1<<t;
                if(mask==0){
                    res=Math.max(res,j-i+1);
                    index=j;
                }
            }
            i=i+index+1;
        }
        return res;
    }

    public int maxRotateFunction(int[] A) {
        int f0=0,sum=0,res=0,n=A.length;
        for(int i=0;i<n;i++){
            sum+=A[i];
            f0+=i*A[i];
        }
        res=f0;
        for (int i = 1; i <n ; i++) {
            f0=f0+sum-n*A[n-i];
            res=Math.max(res,f0);
        }
        return res;
    }
    public int integerReplacement(int n) {
        long N=n;
        int res=0;
        while (N!=1){
            if((N&1)==1) {
                if(N!=3 && (N&2)==2) N+=1;
                else N-=1;
            }else if ((N&1)==0){
                N>>=1;
            }
            res++;
        }
        return  res;
    }

    public static void main(String[] args) {
        System.out.println(new Random().nextInt(1));
    }


}

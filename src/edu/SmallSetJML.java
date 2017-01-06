package edu;

public class SmallSetJML {
    private /*@ spec_public @*/ int[] ear;         
    private /*@ spec_public @*/ int ux = 0;        
    private /*@ spec_public @*/ int sz = 1000;
    public boolean classInv(SmallSetJML s) {
        return
            0 <= s.ux && s.ux < s.sz
            && setOf(s) == setOf(s.ear, 0, ux-1)
            ;
    }
    //@ public invariant this.ux >= 0 && this.ux < this.sz && setOf(this) == setOf(this.ear, 0, ux-1);

    public SmallSetJML() {
        ear = new int[sz];
    }
    
    //@ requires m < n;
    public SmallSetJML(int [] a, int m, int n) {
        assert m < n;
        ear = new int[sz];
        for (int i = m; i < n; i++) {
            insert(a[i]);
        }
    }

    private /*@ spec_public @*/ SmallSetJML setOf(SmallSetJML s) {
        return s.compact();
    }

    private /*@ spec_public @*/ SmallSetJML setOf(int [] a, int m, int n) {
        return setOf(new SmallSetJML(a, m, n));
    }

    //@ requires classInv(this);
    //@ ensures 0 <= \result && \result <= ux && e == ear[\result] ;
    private /*@ spec_public @*/ int indexOf(int e) {
        assert classInv(this);
        int i = 0;
        ear[ux] = e;
        while (ear[i] != e)
            i++;
        assert 0 <= i && i <= ux && e == ear[i] ;
        return i;
    }
   
    //@ ensures indexOf(e) >= ux || (indexOf(e) == ear[indexOf(e)] && 0 <= indexOf(e) && indexOf(e) < ux);
    public boolean isin(int e) {
        int x = indexOf(e);
        assert x >= ux || (x == ear[x] && 0 <= x && x < ux);
        return (x < ux);
    }
    
    //@ ensures setOf(this) == setOf(\result) && \result.ux <= this.ux;
    public SmallSetJML compact() {
        SmallSetJML newset = new SmallSetJML();
        while (ux > 0) {
            int e = ear[ux-1];
            delete(e);
            if (! newset.isin(e))
                newset.insert(e);
        }
        assert setOf(this) == setOf(newset) && newset.ux <= this.ux;
        return newset;
    }
    
    //@ ensures ux == sz - 1 || isin(e);
    public SmallSetJML insert(int e) {
        if (ux < sz - 1) {
            if (ux > 0)
                ear[ux] = ear[0];
            ear[0] = e;
            ux++;
        } else {

        }
        assert ux == sz - 1 || isin(e);
        return this;
    }
  
    //@ requires 0 <= a && a < b;
    //@ ensures 0 <= \result && \result < b - a && ! setOf(ear, a, b).isin(e);
    private int delete(int a, int b, int e) {
        assert 0 <= a && a < b;
        int nd = 0;

        for (int i = a, j = a; i < b; i++) {
            if (ear[i] == e)
                nd ++;
            else {
                if (j < i)
                    ear[j] = ear[i];
                j++;
            }
        }
        assert 0 <= nd && nd < b - a && ! setOf(ear, a, b).isin(e);
        return nd;
    }
    
    //@ requires ux < sz;
    //@ ensures ux < sz && !isin(e);
    public SmallSetJML delete(int e) {
        assert ux < sz;
        ux -= delete(0, ux, e);
        assert ux < sz && !isin(e);
        return this;
    }

    //@ ensures  ux == setOf(this.compact()).nitems();
    public int nitems() {
        SmallSetJML s = compact();
        ux = s.ux;
        ear = s.ear;
        assert ux == setOf(s).nitems();
        return ux;
    }

    // ensures setOf(this) == setOf(old(<this>)).union(setOf(tba));    
    public SmallSetJML union(SmallSetJML tba) {
        SmallSetJML old = this;
        for (int i = 0; i < tba.ux; i++)
            insert(tba.ear[i]);
        assert setOf(this) == setOf(old).union(setOf(tba));
        return this;
    }
    
    // ensures setOf(this) == setOf(old(<this>)).diff(setOf(tbs));
    public SmallSetJML diff(SmallSetJML tbs) {
        SmallSetJML old = this;
        SmallSetJML newset = new SmallSetJML();
        for (int i = 0; i < tbs.ux; i++)
            if (! this.isin(tbs.ear[i])) newset.insert(tbs.ear[i]);
        for (int i = 0; i < this.ux; i++) {
            if (! tbs.isin(this.ear[i])) newset.insert(this.ear[i]);
        }
        ear = newset.ear;
        ux = newset.ux;
        assert setOf(this) == setOf(old).diff(setOf(tbs));
        return this;
    }

    //@ ensures \result !=""; 
    public String toString() {
        String s = "el: ";
        for (int i = 0; i < ux; i++) {
            s += ", " + ear[i];
        }
        s += ", ux=" + ux;
        return s;
    }

    public static void main(String[] args) {
        SmallSetJML s = new SmallSetJML();
        SmallSetJML t = new SmallSetJML();
        int [] a = {1,2,3,4,5,6};
        for (int j = 0; j < 3; j++)
            for (int i = 0; i < a.length; i++) { // or use setOf()

                s.insert(a[i]);
                t.insert(a[i] - 1);
            }
        // some simple tests
        System.out.println("set s " + s + "; nitems=" + s.nitems());
        s.delete(1);
        s.delete(3);
        System.out.println("set s " + s + "; nitems=" + s.nitems());
        s.union(t);
        System.out.println("set s " + s + "; nitems=" + s.nitems());
        t.insert(7);
        t.diff(s);
        System.out.println("set t " + t + "; nitems=" + t.nitems());
    }
}
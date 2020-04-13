package com.bkb;

public class Main {

    public static void main(String[] args) {

        DynamicArrayList<Object> dynamicArrayList = new DynamicArrayList<>();

        System.out.println("Başlangıç durumu!");
        System.out.print("getLength: " + dynamicArrayList.getLength());
        System.out.println("  getSize: " + dynamicArrayList.getSize());
        System.out.println();

        dynamicArrayList.append(3);
        dynamicArrayList.toString();
        System.out.print("getLength: " + dynamicArrayList.getLength());
        System.out.println("  getSize: " + dynamicArrayList.getSize());
        System.out.println();
        
        dynamicArrayList.append(2);
        dynamicArrayList.toString();
        System.out.print("getLength: " + dynamicArrayList.getLength());
        System.out.println("  getSize: " + dynamicArrayList.getSize());
        System.out.println();

        dynamicArrayList.append(5);
        dynamicArrayList.toString();
        System.out.print("getLength: " + dynamicArrayList.getLength());
        System.out.println("  getSize: " + dynamicArrayList.getSize());
        System.out.println();

        dynamicArrayList.append(2);
        dynamicArrayList.toString();
        System.out.print("getLength: " + dynamicArrayList.getLength());
        System.out.println("  getSize: " + dynamicArrayList.getSize());
        System.out.println();

        dynamicArrayList.append("BKB");
        dynamicArrayList.toString();
        System.out.print("getLength: " + dynamicArrayList.getLength());
        System.out.println("  getSize: " + dynamicArrayList.getSize());
        System.out.println();

        dynamicArrayList.append(44);
        dynamicArrayList.toString();
        System.out.print("getLength: " + dynamicArrayList.getLength());
        System.out.println("  getSize: " + dynamicArrayList.getSize());
        System.out.println();

        System.out.println("Remove süreci başlıyor...");
        System.out.println();

        dynamicArrayList.remove(2);
        dynamicArrayList.toString();
        System.out.print("getLength: " + dynamicArrayList.getLength());
        System.out.println("  getSize: " + dynamicArrayList.getSize());
        System.out.println();

        dynamicArrayList.remove(3);
        dynamicArrayList.toString();
        System.out.print("getLength: " + dynamicArrayList.getLength());
        System.out.println("  getSize: " + dynamicArrayList.getSize());
        System.out.println();

        dynamicArrayList.remove(2);
        dynamicArrayList.toString();
        System.out.print("getLength: " + dynamicArrayList.getLength());
        System.out.println("  getSize: " + dynamicArrayList.getSize());
        System.out.println();

        dynamicArrayList.remove(55);
        System.out.println("(Elemanın var olmaması durumu bilerek test edilmiştir.)");
        dynamicArrayList.toString();
        System.out.print("getLength: " + dynamicArrayList.getLength());
        System.out.println("  getSize: " + dynamicArrayList.getSize());
        System.out.println();

        dynamicArrayList.remove(5);
        dynamicArrayList.toString();
        System.out.print("getLength: " + dynamicArrayList.getLength());
        System.out.println("  getSize: " + dynamicArrayList.getSize());
        System.out.println();

        dynamicArrayList.remove("BKB");
        dynamicArrayList.toString();
        System.out.print("getLength: " + dynamicArrayList.getLength());
        System.out.println("  getSize: " + dynamicArrayList.getSize());
        System.out.println();

        dynamicArrayList.remove(44);
        dynamicArrayList.toString();
        System.out.print("getLength: " + dynamicArrayList.getLength());
        System.out.println("  getSize: " + dynamicArrayList.getSize());
        System.out.println();

       /*for(int i = 0; i<10; i++){
           dynamicArrayList.append(4);
           dynamicArrayList.toString();
           System.out.println("Var olan eleman sayısı: " + dynamicArrayList.getLength());
           System.out.println("Bellekte ayrılan yer: " + dynamicArrayList.getSize());
           System.out.println();
       }

        System.out.println("Remove süreci başlıyor...");
        System.out.println();

        for(int i = 0; i<10; i++){
            dynamicArrayList.remove(4);
            dynamicArrayList.toString();
            System.out.println("Var olan eleman sayısı: " + dynamicArrayList.getLength());
            System.out.println("Bellekte ayrılan yer: " + dynamicArrayList.getSize());
            System.out.println();
        }*/
    }
}

class DynamicArrayList<E>{

    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = new Object[1]; //Default Capacity 1 olarak belirlendi
    transient Object[] elementData; //Elemanlarının tutulacağı array
    protected transient int length = 0; //Array'de var olan eleman sayısını tutar
    int capacity; // RAM'de array için ayrılacak alan kapasitesi

    public DynamicArrayList() {
        //Constructor ile array initialize edilir.
        capacity = DEFAULTCAPACITY_EMPTY_ELEMENTDATA.length;
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    public String toString(){
        System.out.print("Elemanlar: ");
        for(Object o : this.elementData){
            System.out.print(o + " ");
        }
        System.out.println();
        return null;
    }

    public int getLength(){
        return length;
    }

    public int getSize(){
        return capacity;
    }

    //Hem append hem de remove methodlarının kullanabileceği ortak bir resize methodu tanımlandı.
    private Object[] resize(int newCapacity) {
        System.out.println("Amortized cost işlemi gerçekleşiyor...");

        //return this.elementData = Arrays.copyOf(this.elementData, newCapacity);
        //Yorum olarak işaretlenmiş üstteki satır ile aşağıdaki döngü temelde aynı işi yapar
        //Complexity hesaplaması yapılabilmesi için temel yapıda olanı tercih ettim.

        Object[] tempArray = new Object[newCapacity]; //Move için geçici bir array tanımlandı
        for(int i=0; i<=this.length-1;i++){
            System.out.println("Move işlemi gerçekleşiyor...");
            tempArray[i] = this.elementData[i];
        }
        return tempArray;
    }

    public void append(E e) {
        if (this.capacity == length) { //Eğer kapasite ile var olan veri sayısı eşit ise;
            this.elementData = this.resize(this.capacity *= 2); //Kapasiteyi 2 ile katlanarak arttır.
        }
        ++this.length;
        System.out.println(e + " elemanı eklendi.");
        this.elementData[length-1] = e; //Kapasite artsa da artmasa da son'a ekleme işlemi gerçekleşir
    }

    public boolean remove(Object o) {
        int i = this.length-1; //Sondan silme gerçekleşeceği için i değişkeni initialize edilir.
        while (true) {
            if (i<0) { //İstenen nesne array'de mevcut değilse false döndürülür.
                System.out.println("İstenilen nesne mevcut değildir.");
                return false;
            }
            if (o.equals(this.elementData[i])) {
                break;
            }
            i--;
        }

        this.length--;
        System.out.println(o + " elemanı çıkarıldı.");
        int k;
        for(k = i; k<this.length; k++){ //Silinen nesne'den sonraki nesneler kaydırılır.
            this.elementData[k] = this.elementData[k+1];
        }
        this.elementData[k] = null;
        if (this.capacity/2 == length) { //Remove işleminde de 2'ye bölünerek allocated memory azaltılır.
            if(this.capacity == 1){ //Kapasite minimum 1 birim yer tutacak şekilde varlığını sürdürsün istedim.
                return true;
            }
            this.elementData = this.resize(this.capacity /= 2);
        }
        return true;
    }
}

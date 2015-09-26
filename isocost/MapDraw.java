package isocost;

public class MapDraw {

    String getCenterCoords(String[][] sCoords) {
        Double[][] coords, dTempCoords;
        int iNumberOfIntervals, iMaxInterY, iMaxInterX;
        int iNumberOfElements;
        int[] iValueOfIntervCountX, iValueOfIntervCountY;
        double[] dIntervalRangeValueX, dIntervalRangeValueY;
        double dLenghtOfIntervalX, dLenghtOfIntervalY;
        double dAverageX, dAverageY;
        int tempCount, iCountC;
        double minX, maxX, minY, maxY;
  
        //инициализация
        coords = new Double[sCoords.length][2];
        iMaxInterY = 0;
        iMaxInterX = 0;
        dAverageX = 0;
        dAverageY = 0;
        tempCount = 0;
        iCountC = 0;
        // конец блока инициализации
        
        for(int i=0; i<sCoords.length;i++){
            if(sCoords[i][0]!=(null)&&sCoords[i][1]!=(null)){
                //System.out.println("sCoords[" + i+ "][0]: " + sCoords[i][0] + ". sCoords[" +i+"][1]: " + sCoords[i][1]);
                coords[tempCount][0]=Double.parseDouble(sCoords[i][0]);
                coords[tempCount++][1]=Double.parseDouble(sCoords[i][1]);
            }
        }
        
        dTempCoords = coords;
        coords = new Double[tempCount][2];
        iNumberOfElements = coords.length;
        
        for(int i=0; i<tempCount; i++){
            coords[i][0]=dTempCoords[i][0];
            coords[i][1]=dTempCoords[i][1];
        }

            minX = coords[0][1];
            maxX = coords[0][1];
            minY = coords[0][0];
            maxY = coords[0][0];

        for (int i = 0; i < iNumberOfElements; i++) {
            if (coords[i][1] > maxX) maxX = (coords[i][1]);
            if (coords[i][1] < minX) minX = (coords[i][1]);
            if (coords[i][0] > maxY) maxY = (coords[i][0]);
            if (coords[i][0] < minY) minY = (coords[i][0]);
        }      
        
        if(sCoords.length>10){
        
        //Расчет количества интервалов и шаг.
        //Если есть мат. функция округления вверх, то использовать её. 
        //Пока добавляем 1 если округлено в меньшую сторону.
        
        iNumberOfIntervals = 5*Math.log10(iNumberOfElements) > Math.round(5*Math.log10(iNumberOfElements))?  
                (int)(5*Math.log10(iNumberOfElements) +1): (int) (5*Math.log10(iNumberOfElements));
        dLenghtOfIntervalX = ((maxX-minX)/iNumberOfIntervals); // Шаг для X
        dLenghtOfIntervalY = ((maxY-minY)/iNumberOfIntervals); // Шаг для Y
        
        
        //создание массивов с интервалами
        iValueOfIntervCountX = new int[iNumberOfIntervals];
        iValueOfIntervCountY = new int[iNumberOfIntervals];
        dIntervalRangeValueX = new double[iNumberOfIntervals];
        dIntervalRangeValueY = new double[iNumberOfIntervals];
        
        for (int i = 0; i < iNumberOfIntervals; i++) {
            if (i == 0) {
                dIntervalRangeValueX[i] = minX + dLenghtOfIntervalX;
                dIntervalRangeValueY[i] = minY + dLenghtOfIntervalY;
            } else {
                dIntervalRangeValueX[i] = dIntervalRangeValueX[i-1] + dLenghtOfIntervalX;
                dIntervalRangeValueY[i] = dIntervalRangeValueY[i-1] + dLenghtOfIntervalY;
            }
        }
        //частота элементов в интервалах
        for (int i = 0; i < iNumberOfElements; i++) {
            int t = 0;
            while(t<iNumberOfIntervals&&coords[i][1]>dIntervalRangeValueX[t]){t++;}
            try{
                iValueOfIntervCountX[t] +=1;
            }catch(ArrayIndexOutOfBoundsException e){iValueOfIntervCountX[t-1] +=1;System.out.println(t);}
             t=0;
            while(t<iNumberOfIntervals&&coords[i][0]>dIntervalRangeValueY[t]){t++;}
            try{
            iValueOfIntervCountY[t] +=1;}
            catch(ArrayIndexOutOfBoundsException e){iValueOfIntervCountY[t-1] +=1;System.out.println(t);}
            
        }
        System.out.println("this");
        for(int i =0; i<iNumberOfIntervals; i++){
            if (iValueOfIntervCountX[iMaxInterX] < iValueOfIntervCountX[i]) iMaxInterX=i;
            if (iValueOfIntervCountY[iMaxInterY] < iValueOfIntervCountY[i]) iMaxInterY=i;
        }
        
        System.out.println("hop " + iValueOfIntervCountX[iMaxInterX] + "\t" + (iValueOfIntervCountX[iMaxInterX] - dLenghtOfIntervalX)
                + "\t" + iValueOfIntervCountY[iMaxInterY] + "\t" + (iValueOfIntervCountY[iMaxInterY] - dLenghtOfIntervalY));
        
        for(int i=0; i<iNumberOfElements; i++){
            if (coords[i][1] <= dIntervalRangeValueX[iMaxInterX]
                    && coords[i][1] > (dIntervalRangeValueX[iMaxInterX] - dLenghtOfIntervalX)
                    && coords[i][0] <= dIntervalRangeValueY[iMaxInterY]
                    && coords[i][0] > (dIntervalRangeValueY[iMaxInterY] - dLenghtOfIntervalY)) {
                //System.out.println("thhh");
                dAverageX += coords[i][1];
                dAverageY += coords[i][0];
                iCountC++;
            }
        }
            
        // dfdf
        
        System.out.println("Extrem. X: " + maxX +" / " + minX);
        System.out.println("Extrem. Y: " + maxY +" / " + minY);
        System.out.println("Step X/Y : " + dLenghtOfIntervalX +" / " + dLenghtOfIntervalY);
        System.out.println("X intervs: " + dIntervalRangeValueX[0] + " / " + dIntervalRangeValueX[dIntervalRangeValueX.length-1]);
        System.out.println("Y intervs: " + dIntervalRangeValueY[0] + " / " + dIntervalRangeValueY[dIntervalRangeValueY.length-1]);
        System.out.println("Intervals: " + iNumberOfIntervals);
        System.out.println("Elements : " + iNumberOfElements);
        //int c =0;
        //for(int i =0; i< iNumberOfIntervals; i++) System.out.println(c++ + "\tX count: " + iValueOfIntervCountX[i] + "\t" + iValueOfIntervCountY[i]);
        //for(int i =0; i<iNumberOfIntervals; i++) System.out.println(c-- + "\t int. X: " + dIntervalRangeValueX[i] + "Y: " + dIntervalRangeValueY[i]);
        System.out.println(iValueOfIntervCountX[0] + " - " + iValueOfIntervCountY[0]);
       
        
        
        System.out.println(iValueOfIntervCountX[iMaxInterX] + " val. for " + iMaxInterX + ": "
        + (iMaxInterX == 0? "< ": dIntervalRangeValueX[iMaxInterX-1] + " - ") + dIntervalRangeValueX[iMaxInterX]);
        System.out.println(iValueOfIntervCountY[iMaxInterY] + " val. for " + iMaxInterY + ": "
        + (iMaxInterY == 0? "< ": dIntervalRangeValueY[iMaxInterY-1] + " - ") + dIntervalRangeValueY[iMaxInterY]);
        }else{
            //System.out.println("else. " +  this.getClass());
            for(int i=0; i<coords.length; i++){
                if(coords[i][1]!=null&&coords[i][0]!=null){
                dAverageX += coords[i][1];
                dAverageY += coords[i][0];
                iCountC++;}
            }
        }
        return dAverageX/iCountC + ", " + dAverageY/iCountC;
    }
}

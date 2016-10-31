package controller;
  import org.lwjgl.LWJGLException;
  import org.lwjgl.input.Controller;
  import org.lwjgl.input.Controllers;
  import jssc.SerialPort;
  import jssc.SerialPortEvent;
  import jssc.SerialPortEventListener;
  import jssc.SerialPortException;
  public class Main {
  static Controller cont;
  public static boolean start;//7
  public static boolean ButtonA;//0
  public static boolean ButtonB;//1
  public static boolean ButtonX;//2
  public static boolean ButtonY;//3
  public static boolean ButtonR;//5
  public static boolean ButtonL;//4
  public static boolean Back;//6
  public static boolean analogLeft;//7
  public static boolean analogRight;//8
  public static double povx;
  public static double povy;
  public static float movableA;
  public static float movableB;
  static SerialPort port;
public static  void main(String[]arg){
	 
	try {
		Controllers.create();
	
	} catch (LWJGLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	Controllers.poll();
 	for(int i=0;i<Controllers.getControllerCount();i++){
 	cont=Controllers.getController(i);
 	System.out.println(cont.getName());
}
 	for(int i=0;i<cont.getAxisCount();i++){
	 	System.out.println(i+": "+cont.getAxisName(i));
	 	cont.setDeadZone(i,(float)0.3);
	}
	for(int i=0;i<cont.getButtonCount();i++){
	 	System.out.println(i+": "+cont.getButtonName(i));
	}
	intiateport();
	  try {port.addEventListener(new PortReader(), SerialPort.MASK_RXCHAR);} catch (SerialPortException e) {e.printStackTrace();}
while(true){
   try{
	cont.poll();
	ButtonA=cont.isButtonPressed(0);
	ButtonB=cont.isButtonPressed(1);
	ButtonX=cont.isButtonPressed(2);
	ButtonY=cont.isButtonPressed(3);
	ButtonL=cont.isButtonPressed(4);
	ButtonR=cont.isButtonPressed(5);
	Back=cont.isButtonPressed(6);
	start=cont.isButtonPressed(7);
	analogLeft=cont.isButtonPressed(8);
	analogRight=cont.isButtonPressed(9);
	povx=cont.getPovX();
	povy=cont.getPovY();
	movableA=cont.getAxisValue(1);
	movableB=cont.getAxisValue(2);

	if(ButtonA){
	  	port.writeBytes("v  ".getBytes());
	    try{ Thread.sleep(10);}catch(Exception ex){ex.printStackTrace();}
	    
	}
	
	else	if(ButtonB){
	 	port.writeBytes("x  ".getBytes());
	    try{ Thread.sleep(10);}catch(Exception ex){ex.printStackTrace();}
		
	}
		
	else if(ButtonX){
	 	port.writeBytes("z  ".getBytes());
	    try{ Thread.sleep(10);}catch(Exception ex){ex.printStackTrace();}
	
	}
		
	else	if(ButtonY)
	{
		port.writeBytes("c  ".getBytes());
	    try{ Thread.sleep(10);}catch(Exception ex){ex.printStackTrace();}
	}
	/*else if(ButtonL)
	{
		port.writeBytes("c  ".getBytes());
	    try{ Thread.sleep(10);}catch(Exception ex){ex.printStackTrace();}
	}
	else if(ButtonR)
	{
		port.writeBytes("c  ".getBytes());
	    try{ Thread.sleep(10);}catch(Exception ex){ex.printStackTrace();}
	}*/
	else if(Back)
	{
		port.writeBytes("o  ".getBytes());
	    try{ Thread.sleep(10);}catch(Exception ex){ex.printStackTrace();}
	}
	else if(start)
	{
		port.writeBytes("b  ".getBytes());
	    try{ Thread.sleep(10);}catch(Exception ex){ex.printStackTrace();}
	}
	else if(analogLeft)
	{
		port.writeBytes("c  ".getBytes());
	    try{ Thread.sleep(10);}catch(Exception ex){ex.printStackTrace();
	    System.out.println("yes");}
	}
	else if(analogRight)
	{
		port.writeBytes("c  ".getBytes());
	    try{ Thread.sleep(10);}catch(Exception ex){ex.printStackTrace();}
	}
	
	else if(movableA>0)
	{
		int value=(int)(movableA*(-10));
		port.writeBytes((value+"e ").getBytes());
		System.out.println(value);
		try{ Thread.sleep(10);}catch(Exception ex){ex.printStackTrace();}
	}
	else if(movableA<0){
	
		int value=(int)(movableA*(-10));
		port.writeBytes((value+"q ").getBytes());
		System.out.println(value);
	    try{ Thread.sleep(10);}catch(Exception ex){ex.printStackTrace();}
	
	}
		
	else if(movableB>0){

		int value=(int)(movableB*(-10));
		port.writeBytes((value+"d ").getBytes());
		System.out.println(value);
	    try{ Thread.sleep(10);}catch(Exception ex){ex.printStackTrace();}
	
	}
	
	else if(movableB<0){
		int value=(int)(movableA*(-10));
		port.writeBytes((value+"a ").getBytes());
		System.out.println(value);
	    try{ Thread.sleep(10);}catch(Exception ex){ex.printStackTrace();}
	
	}
	
	else if (cont.getAxisValue(4)<0){
		
		int value=(int)(cont.getAxisValue(4)*(-10));
		port.writeBytes((value+"w ").getBytes());
		System.out.println(value);
	    try{ Thread.sleep(10);}catch(Exception ex){ex.printStackTrace();}
	}
	else if (cont.getAxisValue(4)>0)
	{
		int value=(int)(cont.getAxisValue(4)*(-10));
		port.writeBytes((value+"s ").getBytes());
	    try{ Thread.sleep(10);}catch(Exception ex){ex.printStackTrace();}
	}
	 if(povy<0)	{
		 port.writeBytes(" c ".getBytes());
		    try{ Thread.sleep(10);}catch(Exception ex){ex.printStackTrace();}
	 }
	
	 if(povy>0)	{
		 port.writeBytes(" c ".getBytes());
		    try{ Thread.sleep(10);}catch(Exception ex){ex.printStackTrace();}
	 }

	 if(povx>0){
		 port.writeBytes(" c ".getBytes());
		    try{ Thread.sleep(10);}catch(Exception ex){ex.printStackTrace();}
	 }
	
	 if(povx<0){
		 port.writeBytes(" c ".getBytes());
		    try{ Thread.sleep(10);}catch(Exception ex){ex.printStackTrace();}
	 }

	
}catch (SerialPortException exception) {
exception.printStackTrace();
}}
}
public static void intiateport(){
	 port=new SerialPort("COM6");
     try{
     if(port.openPort()){
   	  System.out.println("sucess to connct");
   	 try{ Thread.sleep(1000);}catch(Exception ex){ex.printStackTrace();}
   	  port.setParams(SerialPort.BAUDRATE_9600,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);;
///////////////////////////////////////////////////////////////////////   	
//   	 port.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN | 
//                 SerialPort.FLOWCONTROL_RTSCTS_OUT);
//////////////////////////////////////////////////////////////////////  
}else
	 System.out.println("sucess to connct");
     }catch(SerialPortException exception){exception.printStackTrace();}
     }


private static class PortReader  implements SerialPortEventListener {

    @Override
    public void serialEvent(SerialPortEvent event) {
        if(event.isRXCHAR() && event.getEventValue() > 0) {
            try {
                String receivedData = port.readString(event.getEventValue());
                System.out.println("Received response: " + receivedData);
            }
            catch (SerialPortException ex) {
                System.out.println("Error in receiving string from COM-port: " + ex);
            }
        }
    }

} 
}


 
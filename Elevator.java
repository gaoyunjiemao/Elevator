/**
 *
 * Class elevator
 *
 */

// import java.util.*;

public class Elevator
{

	private final int MAX_FLOOR;
	private final int CAPACITY;
	private char[][] structure;
	private int currentFloor;				// current floor of elevator
	private int currentPassengers;			// current passenger inside elevator
	private boolean direction;				// direction 1 up, 0 down
	private int[] buttons;					// button inside cabine of elevator
	private boolean[][] floorButtons;

	// constructor
	public Elevator()
	{
		this(4, 16); // default 4 floor 16 passengerS
	}

	public Elevator(int max, int cap)
	{
		this.MAX_FLOOR = max;
		this.CAPACITY = cap;
		this.currentPassengers =0;
		this.currentFloor =1;
		this.direction=true;// go up
		this.buttons = new int[max];
		this.floorButtons = new boolean [this.MAX_FLOOR][this.MAX_FLOOR];
		//
		this.initStructure();
		//

	}

	public void goUp()
	{
		this.currentFloor= this.currentFloor>=this.MAX_FLOOR? this.MAX_FLOOR : this.currentFloor+1;
	}

	public void goDown()
	{
		this.currentFloor= this.currentFloor==1? 1: this.currentFloor-1;
	}

	public int getOn()
	{
		int rs=0;
		// custom code here
		this.currentPassengers = this.currentPassengers >= this.CAPACITY ? this.CAPACITY : this.currentPassengers+1;
		return rs;
	}

	public int getOff()
	{
		int rs =0;
		//custome code here
		this.currentPassengers = this.currentPassengers <= 0 ? 0 : this.currentPassengers-1;
		return rs;
	}

	public static void main(String[] args) throws InterruptedException
	{
		Elevator objElevator = new Elevator();
		objElevator.simulate();
	}

	public void simulate() throws InterruptedException
	{
		this.test1();
	}

	private void test1()
	{
		System.out.println("Running Test #1");
		int getPeopleAtFloor = 4, enterPeople = 4, arrivalFloor = 2;
		currentFloor = 4;

		// 电梯在一楼
		System.out.println("电梯在" + currentFloor + "楼");
		this.drawStructure();

		// 二楼门外2👤按了⬆️
		System.out.println(getPeopleAtFloor + "楼门外" + enterPeople + "👤按了⬆️");
		moveElevatorToFloor(getPeopleAtFloor);

		// 电梯在二楼开门，2👤进去
		System.out.println("电梯在" + currentFloor + "楼开门，" + enterPeople + "👤进去");

		while (enterPeople > 0)
		{
			this.getOn();
			enterPeople--;
		}
		this.drawStructure();

		// 这2👤 在电梯里面按了三楼
		System.out.println("这" + enterPeople + "👤在电梯里面按了" + arrivalFloor + "楼");
		moveElevatorToFloor(arrivalFloor);

		// 电梯去了三楼
		System.out.println("电梯去了" + currentFloor + "楼");
		System.out.println("Finished!");
	}

	private void moveElevatorToFloor(int floor)
	{
		int movement = floor - currentFloor;

		while (Math.abs(movement) > 0)
		{
			if (movement > 0)
			{
				this.goUp();
				movement--;
			}
			else
			{
				this.goDown();
				movement++;
			}
			this.drawStructure();
		}
	}

	public void stopAt(int floor)
	{

	}

	private void movePassengers(int fromFloor, int toFloor)
	{

	}

	private void initStructure()
	{
		int cabinDimension = (int) (Math.sqrt(this.CAPACITY) + 2);  // lines
		int frameworkDimension = this.MAX_FLOOR*cabinDimension;		// columns
		//allocate structure object
		this.structure = new char[cabinDimension][frameworkDimension];

		// draw frame and borders
		this.drawFramework();
		// draw Empty space  in structure
		this.drawCabine(1, 0);    // draw zero passenger in first initialize structure
	}

	public void drawCabine(int floor, int passengers)
	{
		int cabinDimension = (int) (Math.sqrt(this.CAPACITY)+2);  // cabine withoud borders
		int frameworkDimension = this.MAX_FLOOR*cabinDimension;		// columns
		// draw Empty space  in structure
		int offsetCol = (floor-1)*cabinDimension;
		for(int i =1; i< cabinDimension-1; i++)	// lines
		{
			for(int j=1; j< cabinDimension-1; j++)	//columns
			{
				if(passengers>0){
					this.structure[i][j+offsetCol] = 'O';// draw O  for a passenger
					passengers--;
				}
				else this.structure[i][j+offsetCol] = 'E';				//draw E for empty space
			}
		}
	}

	public void drawFramework()
	{
		int cabinDimension = (int) (Math.sqrt(this.CAPACITY) + 2);  // lines
		int frameworkDimension = this.MAX_FLOOR*cabinDimension;		// columns

		for(int i =0; i< cabinDimension; i++)
		{
			int k =1;
			for(int j=0; j< frameworkDimension; j++)
			{
				/// draw border
				if(i==0 || i == cabinDimension-1 || j==0 || j==frameworkDimension-1) this.structure[i][j] = '#';
				else
					if(j == k*(cabinDimension)-1)
					{
						this.structure[i][j] = '|';
						k++;// next stick line
					}
					else this.structure[i][j] = ' ';
			}
		}
	}

	public void drawStructure()
	{
		// draw frame and borders
		this.drawFramework();
		// draw Empty space  in structure
		this.drawCabine(this.currentFloor, this.currentPassengers);    // draw zero passenger in first initialize structure
		// print to console
		this.printStructure();
		try {
			// thread to sleep for 1000 milliseconds
			Thread.sleep(1000);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void printStructure()
	{
		int cabinDimension = (int) (Math.sqrt(this.CAPACITY) + 2);  // lines
		for(int i =0; i< cabinDimension; i++)
		{
			System.out.print(this.structure[i]);  // print lines to console
			System.out.println("");// next line
		}
	}
}

package by.framework.lab1;

public enum Filling {
	
	CHERRY {
		
		@Override
		public int getColories() {
			return 1;
		}
		
	},
	
	STRAWBERRIES {
		
		@Override
		public int getColories() {
			return 2;
		}
		
	},
	
	PEAR {

		@Override
		public int getColories() {
			return 3;
		}
		
	},
	
	APPLE {

		@Override
		public int getColories() {
			return 4;
		}
		
	},
	CHEESE {

		@Override
		public int getColories() {
			return 5;
		}
	};
	
	public abstract int getColories();

}

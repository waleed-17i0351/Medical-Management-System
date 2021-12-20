package businessLogic;

public class Inventory {
	Medicine[] meds;

	public Medicine[] getMeds() {
		return meds;
	}

	public void setMeds(Medicine[] meds) {
		this.meds = meds;
	}

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	int current;

	public Inventory() {
		meds = new Medicine[50];
		current = 0;
	}

	public void addMed(Medicine med) throws InventoryFullException {
		if (current < meds.length) {
			meds[current] = med;
			current++;
		} else {
			throw new InventoryFullException("Unfortunately, the inventory is full and more medicine cannot be added");
		}

	}

	public void removeMed(String medName) {
		int indexD = 0;
		for (int i = 0; i < meds.length; i++) {
			if (medName.equals(meds[i].getName())) {
				indexD = i;
				break;
			}
		}

		for (int i = indexD; i < meds.length; i++) {
			meds[i] = meds[i + 1];
		}

		current--;
	}

	public void displayInv() {
		for (int i = 0; i < current; i++) {
			meds[i].displayMed();
		}
	}

}

package com.designpatterns.behavioral.iterator;

public class CollectionsOfNames implements Container {
	public String name[] = { "David", "Ram", "John", "Deryk", "Kevin" };

	@Override
	public Iterator getIterator() {
		return new CollectionofNamesIterate();
	}

	private class CollectionofNamesIterate implements Iterator {
		int i;

		@Override
		public boolean hasNext() {
			if (i < name.length) {
				return true;
			}
			return false;
		}

		@Override
		public Object next() {
			if (this.hasNext()) {
				return name[i++];
			}
			return null;
		}
	}

}

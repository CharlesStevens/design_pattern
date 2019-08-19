package pack;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MyObserver implements PropertyChangeListener {
    public MyObserver(MyModel model) {
        model.addChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
       System.out.println("MyObserver called......  Changed property: " + event.getPropertyName() + " [old -> "
                 + event.getOldValue() + "] | [new -> " + event.getNewValue() +"]");
    }
} 
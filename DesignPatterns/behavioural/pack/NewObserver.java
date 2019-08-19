package pack;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class NewObserver implements PropertyChangeListener {
    public NewObserver(MyModel model) {
        model.addChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
       System.out.println("NewObserver called......  Changed property: " + event.getPropertyName() + " [old -> "
                 + event.getOldValue() + "] | [new -> " + event.getNewValue() +"]");
    }
} 
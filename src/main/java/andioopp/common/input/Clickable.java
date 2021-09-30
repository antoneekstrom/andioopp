package andioopp.common.input;

import andioopp.common.transform.Dimension;
import andioopp.common.transform.Vector3f;

public interface Clickable {
    Vector3f getPosition();
    Dimension getSize();
}

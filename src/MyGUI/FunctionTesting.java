package MyGUI;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import javax.sound.midi.Sequence;

class FunctionTesting {
    DrawArea panel;
    DrawArea panel2;

    @Test
    public void panelClearHistory() {
        panel = new DrawArea();
        panel.clearHistory();

        panel2 = new DrawArea();
        assertEquals(panel.getHistory(), panel2.getHistory());
    }

}
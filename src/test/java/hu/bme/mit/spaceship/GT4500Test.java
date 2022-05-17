package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {
  private TorpedoStore mockTS1, mockTS2;
  private GT4500 ship;

  @BeforeEach
  public void init(){
    mockTS1 = mock(TorpedoStore.class);
    mockTS2 = mock(TorpedoStore.class);
    this.ship = new GT4500(mockTS1, mockTS2);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(mockTS1.fire(1)).thenReturn(true);
    when(mockTS2.fire(1)).thenReturn(false); 
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    //assertEquals(true, result);
    verify(mockTS1,times(1)).fire(1); 
    verify(mockTS2,times(0)).fire(1); 
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(mockTS1.fire(1)).thenReturn(true);
    when(mockTS2.fire(1)).thenReturn(true); 
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    //assertEquals(true, result);
    verify(mockTS1,times(1)).fire(1); 
    verify(mockTS2,times(1)).fire(1); 
  }

  // Test #1 
  //Checks that for the first time the primary store is fired. even if both exist
  @Test
  public void fireTorpedo_Single_First_Success(){
    // Arrange
    when(mockTS1.fire(1)).thenReturn(true);
    when(mockTS2.fire(1)).thenReturn(true); 
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    //assertEquals(true, result);
    verify(mockTS1,times(1)).fire(1); 
    verify(mockTS2,times(0)).fire(1); 
  }

  // Test #2
  // Checks that the firing does alternate
  @Test
  public void fireTorpedo_Single_Alternate(){
    // Arrange
    when(mockTS1.fire(1)).thenReturn(true);
    when(mockTS2.fire(1)).thenReturn(true); 
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    //assertEquals(true, result);
    verify(mockTS1,times(1)).fire(1); 
    verify(mockTS2,times(1)).fire(1); 
  }
  
  // Test #3
  // Checks that nothing is fired if both are empty
  @Test
  public void fireTorpedo_All_Empty_Failure(){
    // Arrange
    when(mockTS1.isEmpty()).thenReturn(true);
    when(mockTS2.isEmpty()).thenReturn(true); 
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    //assertEquals(true, result);
    verify(mockTS1,times(0)).fire(1); 
    verify(mockTS2,times(0)).fire(1); 
  }

  // Test #4
  // Checks that second one is fired if first is empty
  @Test
  public void fireTorpedo_Single_First_Empty(){
    // Arrange
    when(mockTS1.isEmpty()).thenReturn(true);
    when(mockTS2.fire(1)).thenReturn(true); 
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    //assertEquals(true, result);
    verify(mockTS1,times(0)).fire(1); 
    verify(mockTS2,times(1)).fire(1); 
  }


  // Test #5
  // Checks that second one is not fired if first fails
  @Test
  public void fireTorpedo_Single_First_Fails(){
    // Arrange
    when(mockTS1.fire(1)).thenReturn(false);
    when(mockTS2.fire(1)).thenReturn(true); 
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    //assertEquals(true, result);
    verify(mockTS1,times(1)).fire(1); 
    verify(mockTS2,times(0)).fire(1); 
  }

    // Test #6
  // Checks that second one is not fired if first fails
  @Test
  public void fireTorpedo_Single_Second_First_Empty(){
    // Arrange
    when(mockTS1.fire(1)).thenReturn(true);
    when(mockTS2.isEmpty()).thenReturn(true); 

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE); //Fire first
    result = ship.fireTorpedo(FiringMode.SINGLE); //Fire first

    // Assert
    //assertEquals(true, result);
    verify(mockTS1,times(2)).fire(1); 
    verify(mockTS2,times(0)).fire(1); 
  }
}

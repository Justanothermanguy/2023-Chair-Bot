/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

// Defining imports
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.XboxController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Robot extends TimedRobot {

  // Create motor instances
  private final CANSparkMax m_rightMotor = new CANSparkMax(1, MotorType.kBrushless);
  private final CANSparkMax m_rightFollower = new CANSparkMax(2, MotorType.kBrushless);
  private final CANSparkMax m_leftMotor = new CANSparkMax(3, MotorType.kBrushless);
  private final CANSparkMax m_leftFollower = new CANSparkMax(4, MotorType.kBrushless);

  // Create drive instance

  private final DifferentialDrive m_robotDrive = new DifferentialDrive(m_leftMotor, m_rightMotor);

  // Create Xbox Controller instance

  private final XboxController m_driverController = new XboxController(0);

  /**
   * This function runs when the robot is first started up. Insert robot
   * initialization code in
   * this function
   */

  // Sets initial motor state on robot start
  @Override
  public void robotInit() {
    /*
     * Invert one side of the drivetrain so that positive voltages
     * result in both sides moving forward. The practice robot's
     * gearbox is constructed such that we have to invert the right side.
     * Also set the follow motors to follow the respective lead motors
     */
    m_rightMotor.setInverted(true);
    m_leftFollower.follow(m_leftMotor);
    m_rightFollower.follow(m_rightMotor);
  }

  /*
   * Removing autonomous code for now becasue we don't need it
   * 
   * @Override
   * public void autonomousInit() {
   * }
   * 
   * @Override
   * public void autonomousPeriodic() {
   * }
   */

  // Sets motor state on teleop start
  @Override
  public void teleopInit() {
    m_leftMotor.setIdleMode(IdleMode.kBrake);
    m_rightMotor.setIdleMode(IdleMode.kBrake);
    m_leftFollower.setIdleMode(IdleMode.kBrake);
    m_rightFollower.setIdleMode(IdleMode.kBrake);
  }

  // Runs when receiving input from the driver controller
  @Override
  public void teleopPeriodic() {
    m_robotDrive.arcadeDrive(-m_driverController.getRightY(), m_driverController.getRightX(), true);
  }

  /*
   * Removing test code for now becasue we don't need it
   * 
   * @Override
   * public void testInit() {
   * }
   * 
   * @Override
   * public void testPeriodic() {
   * }
   */
}
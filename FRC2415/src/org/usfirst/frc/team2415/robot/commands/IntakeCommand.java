package org.usfirst.frc.team2415.robot.commands;

import org.usfirst.frc.team2415.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeCommand extends Command {
	
	long jamTime, startTime;
	boolean senseCurrent = false;
	double sign = 0;
	
	double intakeSpeed = 0.5;
	double outtakeSpeed = -0.5;
	double currentCap = 26;
	double reverseTime = 0.05;
	

    public IntakeCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.intakeSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//TODO: extend intake
    	if(Robot.gearManipulatorSubsystem.isExtended){
        	Robot.intakeSubsystem.setSolenoid(true);
        	startTime = System.currentTimeMillis()/1000;
        	Robot.intakeSubsystem.setMotor(0);	
        	Robot.intakeSubsystem.isExtended = true;
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.gearManipulatorSubsystem.isExtended){
    		if(Robot.intakeSubsystem.getCurrent() >= currentCap && System.currentTimeMillis()/1000 - startTime >= 0.25 && Math.signum(Robot.intakeSubsystem.getMotor()) == sign){
        		jamTime = System.currentTimeMillis()/1000;
        	}
        	
        	if(System.currentTimeMillis()/1000 - jamTime <= reverseTime) {
        		Robot.intakeSubsystem.setMotor(outtakeSpeed);
        	} else Robot.intakeSubsystem.setMotor(intakeSpeed);
        	
        	sign = Robot.intakeSubsystem.getMotor()/(Math.abs(Robot.intakeSubsystem.getMotor()));
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	//TODO: retract intake
    	Robot.intakeSubsystem.setSolenoid(false);
    	Robot.intakeSubsystem.setMotor(0);
    	Robot.intakeSubsystem.isExtended = false;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	//TODO: retract intake
    	Robot.intakeSubsystem.setSolenoid(false);
    	Robot.intakeSubsystem.setMotor(0);
    	Robot.intakeSubsystem.isExtended = false;
    }
}
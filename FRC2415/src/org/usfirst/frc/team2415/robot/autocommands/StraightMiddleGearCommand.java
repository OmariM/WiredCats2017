package org.usfirst.frc.team2415.robot.autocommands;

import org.usfirst.frc.team2415.robot.Robot;
import org.usfirst.frc.team2415.robot.commands.GearOuttakeCommand;
import org.usfirst.frc.team2415.robot.commands.ZeroEncoders;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class StraightMiddleGearCommand extends CommandGroup {

    public StraightMiddleGearCommand() {
        
    	requires(Robot.driveSubsystem);
    	
    	addSequential(new DriveStraightToCommand(4.7, 0.35, 3));
    	addSequential(new WaitCommand(0.5));
    	addSequential(new ZeroEncoders());
    	addSequential(new DriveStraightToCommand(2.0, 0.20, .75));
    	addSequential(new WaitCommand(0.25));
    	addParallel(new GearOuttakeCommand(0.1));
    	
    }
}

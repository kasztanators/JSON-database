package com.griddynamics.internship.client.args;

import com.beust.jcommander.Parameter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FileCommandArgs implements CommandArgs{
    @Parameter(names = "-in")
    private String filename;
}

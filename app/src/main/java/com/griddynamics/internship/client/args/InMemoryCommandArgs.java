package com.griddynamics.internship.client.args;

import com.beust.jcommander.Parameter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InMemoryCommandArgs implements CommandArgs {
    @Parameter(names = "-t", description = "request type")
    private String type;
    @Parameter(names = "-k",  description = "database key")
    private String key;
    @Parameter(names = "-v", description = "database input data")
    private String value;
}

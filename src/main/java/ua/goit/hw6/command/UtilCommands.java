package ua.goit.hw6.command;

import ua.goit.hw6.model.dto.DeveloperDto;
import ua.goit.hw6.service.DeveloperService;
import ua.goit.hw6.view.View;

import java.util.List;

public class UtilCommands implements Command {
    private static final String SALARY_COMMANDS = "util";
    private final View view;
    private final DeveloperService developerService;

    public UtilCommands(View view, DeveloperService developerService) {
        this.view = view;
        this.developerService = developerService;
    }

    @Override
    public boolean canExecute(String input) {
        return input.split(" ")[0].equals(SALARY_COMMANDS);
    }

    @Override
    public void execute(String input) {
        String[] args = input.split(" ");
        try {
            switch (args[1]) {
                case "-s" -> getTotalSalaryByProject(args);
            }
        } catch (RuntimeException e) {
            view.write("parameters incorrect");
        }

    }

    private void getTotalSalaryByProject(String[] args) {
        List<DeveloperDto> developerDtoList = developerService.getByProjectId(Long.valueOf(args[2]));
        int totalSalary = developerDtoList.stream()
                .map(DeveloperDto::getSalary)
                .mapToInt(Integer::intValue)
                .sum();
        view.write("Total salary: " + totalSalary);
    }
}

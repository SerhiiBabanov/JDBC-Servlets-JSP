package ua.goit.hw6.service.conventer;

import ua.goit.hw6.model.dao.ProjectDao;
import ua.goit.hw6.model.dto.ProjectDto;

public class ProjectConverter implements Converter<ProjectDto, ProjectDao> {
    @Override
    public ProjectDto from(ProjectDao projectDao) {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(projectDao.getId());
        projectDto.setName(projectDao.getName());
        projectDto.setGit_url(projectDao.getGit_url());
        projectDto.setCost(projectDao.getCost());
        projectDto.setDate(projectDao.getDate());
        return projectDto;
    }

    @Override
    public ProjectDao to(ProjectDto projectDto) {
        ProjectDao projectDao = new ProjectDao();
        projectDao.setId(projectDto.getId());
        projectDao.setName(projectDto.getName());
        projectDao.setGit_url(projectDto.getGit_url());
        projectDao.setCost(projectDto.getCost());
        projectDao.setDate(projectDto.getDate());
        return projectDao;
    }
}

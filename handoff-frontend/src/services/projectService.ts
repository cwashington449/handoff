import { apiClient } from '@/lib/api';
import { 
  Project, 
  ProjectCreateRequest, 
  ProjectUpdateRequest, 
  ProjectApplication,
  ProjectApplicationCreateRequest,
  ProjectApplicationStatusUpdateRequest 
} from '@/types';

export const projectService = {
  // Project CRUD operations
  createProject: async (data: ProjectCreateRequest): Promise<Project> => {
    const response = await apiClient.post<Project>('/projects', data);
    return response.data;
  },

  getProject: async (id: string): Promise<Project> => {
    const response = await apiClient.get<Project>(`/projects/${id}`);
    return response.data;
  },

  updateProject: async (id: string, data: ProjectUpdateRequest): Promise<Project> => {
    const response = await apiClient.put<Project>(`/projects/${id}`, data);
    return response.data;
  },

  deleteProject: async (id: string): Promise<void> => {
    await apiClient.delete(`/projects/${id}`);
  },

  // Get user's projects
  getMyProjects: async (): Promise<Project[]> => {
    const response = await apiClient.get<Project[]>('/projects/mine');
    return response.data;
  },

  // Get projects by status
  getProjectsByStatus: async (status: 'DRAFT' | 'OPEN' | 'IN_PROGRESS' | 'COMPLETED' | 'CANCELLED'): Promise<Project[]> => {
    const response = await apiClient.get<Project[]>(`/projects/status/${status}`);
    return response.data;
  },

  // Publish project
  publishProject: async (id: string): Promise<Project> => {
    const response = await apiClient.post<Project>(`/projects/${id}/publish`);
    return response.data;
  },

  // Increment view count
  incrementViewCount: async (id: string): Promise<void> => {
    await apiClient.post(`/projects/${id}/view`);
  },

  // Project Applications
  applyToProject: async (projectId: string, data: ProjectApplicationCreateRequest): Promise<ProjectApplication> => {
    const response = await apiClient.post<ProjectApplication>(`/projects/${projectId}/applications`, data);
    return response.data;
  },

  getProjectApplications: async (projectId: string): Promise<ProjectApplication[]> => {
    const response = await apiClient.get<ProjectApplication[]>(`/projects/${projectId}/applications`);
    return response.data;
  },

  getMyApplications: async (): Promise<ProjectApplication[]> => {
    const response = await apiClient.get<ProjectApplication[]>('/applications/mine');
    return response.data;
  },

  updateApplicationStatus: async (applicationId: string, data: ProjectApplicationStatusUpdateRequest): Promise<ProjectApplication> => {
    const response = await apiClient.patch<ProjectApplication>(`/applications/${applicationId}/status`, data);
    return response.data;
  },

  deleteApplication: async (applicationId: string): Promise<void> => {
    await apiClient.delete(`/applications/${applicationId}`);
  },
};
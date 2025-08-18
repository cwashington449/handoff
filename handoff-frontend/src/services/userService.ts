import { apiClient } from '@/lib/api';
import { User, UserUpdateRequest } from '@/types';

export const userService = {
  // Get user profile
  getProfile: async (): Promise<User> => {
    const response = await apiClient.get<User>('/users/profile');
    return response.data;
  },

  // Update user profile
  updateProfile: async (data: UserUpdateRequest): Promise<User> => {
    const response = await apiClient.put<User>('/users/profile', data);
    return response.data;
  },

  // Deactivate user account
  deactivateAccount: async (): Promise<void> => {
    await apiClient.delete('/users/profile');
  },
};
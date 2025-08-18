import { apiClient, tokenStorage } from '@/lib/api';
import { LoginRequest, RegisterRequest, AuthResponse } from '@/types';

export const authService = {
  // Register new user
  register: async (data: RegisterRequest): Promise<AuthResponse> => {
    const response = await apiClient.post<AuthResponse>('/auth/register', data);
    const { user, token } = response.data;
    
    // Store token
    tokenStorage.set(token);
    
    return { user, token };
  },

  // Login user
  login: async (data: LoginRequest): Promise<AuthResponse> => {
    const response = await apiClient.post<AuthResponse>('/auth/login', data);
    const { user, token } = response.data;
    
    // Store token
    tokenStorage.set(token);
    
    return { user, token };
  },

  // Logout user
  logout: (): void => {
    tokenStorage.remove();
    window.location.href = '/login';
  },

  // Check if user is authenticated
  isAuthenticated: (): boolean => {
    const token = tokenStorage.get();
    return !!token;
  },

  // Get stored token
  getToken: (): string | null => {
    return tokenStorage.get();
  },

  // Get current user profile
  getCurrentUser: async () => {
    const response = await apiClient.get('/users/profile');
    return response.data;
  },
};
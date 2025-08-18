// User types
export interface User {
  id: string;
  email: string;
  firstName: string;
  lastName: string;
  role: 'CREATOR' | 'FINISHER' | 'BOTH';
  status: 'ACTIVE' | 'INACTIVE' | 'SUSPENDED';
  profileJson?: Record<string, any>;
  preferencesJson?: Record<string, any>;
  skills?: string[];
  createdAt: string;
  updatedAt: string;
  lastLoginAt?: string;
}

// Auth types
export interface LoginRequest {
  email: string;
  password: string;
}

export interface RegisterRequest {
  email: string;
  password: string;
  firstName: string;
  lastName: string;
  role: 'CREATOR' | 'FINISHER' | 'BOTH';
}

export interface AuthResponse {
  user: User;
  token: string;
}

// Project types
export interface Project {
  id: string;
  title: string;
  description: string;
  status: 'DRAFT' | 'OPEN' | 'IN_PROGRESS' | 'COMPLETED' | 'CANCELLED';
  complexity: 'SIMPLE' | 'MODERATE' | 'COMPLEX';
  budgetMin: number;
  budgetMax: number;
  budgetCurrency: string;
  estimatedTimeline: string;
  requirementsJson?: Record<string, any>;
  attachmentsJson?: Record<string, any>;
  requiredSkills: string[];
  applicationDeadline?: string;
  viewCount: number;
  creatorId: string;
  assignedFinisherId?: string;
  createdAt: string;
  updatedAt: string;
}

export interface ProjectCreateRequest {
  title: string;
  description: string;
  requirementsJson?: Record<string, any>;
  budgetMin: number;
  budgetMax: number;
  budgetCurrency?: string;
  estimatedTimeline: string;
  requiredSkills: string[];
  attachmentsJson?: Record<string, any>;
  applicationDeadline?: string;
}

export interface ProjectUpdateRequest extends Partial<ProjectCreateRequest> {}

// Project Application types
export interface ProjectApplication {
  id: string;
  projectId: string;
  finisherId: string;
  status: 'PENDING' | 'ACCEPTED' | 'REJECTED' | 'WITHDRAWN';
  coverLetter: string;
  bidAmount: number;
  proposedTimeline: string;
  attachmentsJson?: Record<string, any>;
  createdAt: string;
  updatedAt: string;
}

export interface ProjectApplicationCreateRequest {
  coverLetter: string;
  bidAmount: number;
  proposedTimeline: string;
  attachmentsJson?: Record<string, any>;
}

export interface ProjectApplicationStatusUpdateRequest {
  status: 'ACCEPTED' | 'REJECTED';
}

// Message types
export interface Message {
  id: string;
  senderId: string;
  receiverId: string;
  projectId?: string;
  content: string;
  messageType: 'TEXT' | 'FILE' | 'SYSTEM';
  attachmentsJson?: Record<string, any>;
  readAt?: string;
  createdAt: string;
  updatedAt: string;
}

export interface MessageCreateRequest {
  receiverId: string;
  projectId?: string;
  content: string;
  messageType?: 'TEXT' | 'FILE';
  attachmentsJson?: Record<string, any>;
}

// Payment types
export interface Payment {
  id: string;
  projectId: string;
  payerId: string;
  payeeId: string;
  amount: number;
  currency: string;
  status: 'PENDING' | 'PROCESSING' | 'COMPLETED' | 'FAILED' | 'REFUNDED';
  paymentType: 'ESCROW' | 'MILESTONE' | 'FINAL';
  platformFeeAmount: number;
  stripePaymentIntentId?: string;
  metadata?: Record<string, any>;
  createdAt: string;
  updatedAt: string;
}

// User Profile Update
export interface UserUpdateRequest {
  firstName?: string;
  lastName?: string;
  profileJson?: Record<string, any>;
  preferencesJson?: Record<string, any>;
  skills?: string[];
}

// Lead capture (for existing components)
export interface LeadCaptureData {
  name: string;
  email: string;
  type: 'creator' | 'finisher';
  timestamp: number;
  userAgent?: string;
  referrer?: string;
}
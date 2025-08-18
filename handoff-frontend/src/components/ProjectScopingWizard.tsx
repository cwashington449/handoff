import { useState } from 'react';
import { Card, CardContent, CardHeader, CardTitle } from '@/components/ui/card.tsx';
import { Button } from '@/components/ui/button.tsx';
import { Input } from '@/components/ui/input.tsx';
import { Textarea } from '@/components/ui/textarea.tsx';
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from '@/components/ui/select.tsx';
import { Checkbox } from '@/components/ui/checkbox.tsx';
import { Badge } from '@/components/ui/badge.tsx';
import { Progress } from '@/components/ui/progress.tsx';
import { ArrowLeft, ArrowRight, CheckCircle, FileText, Zap } from 'lucide-react';
import { analytics } from '@/lib/analytics.ts';

interface ProjectData {
  projectName: string;
  projectType: string;
  description: string;
  budget: string;
  timeline: string;
  features: string[];
  hasDesigns: boolean;
  hasContent: boolean;
  hasBackend: boolean;
  targetAudience: string;
  successMetrics: string;
}

const projectTypes = [
  'Web Application',
  'Mobile App (iOS/Android)',
  'E-commerce Store',
  'Landing Page/Website',
  'SaaS Platform',
  'API/Backend Service',
  'Browser Extension',
  'Desktop Application',
  'Other'
];

const commonFeatures = [
  'User Authentication',
  'Payment Processing',
  'Real-time Updates',
  'Email Notifications',
  'File Uploads',
  'Search Functionality',
  'Analytics Dashboard',
  'Mobile Responsive',
  'API Integration',
  'Database Management',
  'Admin Panel',
  'Social Media Integration'
];

const budgetRanges = [
  'Under $5,000',
  '$5,000 - $10,000',
  '$10,000 - $25,000',
  '$25,000 - $50,000',
  '$50,000+'
];

const timelineOptions = [
  '2-4 weeks',
  '1-2 months',
  '2-3 months',
  '3-6 months',
  '6+ months'
];

export function ProjectScopingWizard() {
  const [isOpen, setIsOpen] = useState(false);
  const [step, setStep] = useState(1);
  const [isSubmitted, setIsSubmitted] = useState(false);
  const [projectData, setProjectData] = useState<ProjectData>({
    projectName: '',
    projectType: '',
    description: '',
    budget: '',
    timeline: '',
    features: [],
    hasDesigns: false,
    hasContent: false,
    hasBackend: false,
    targetAudience: '',
    successMetrics: ''
  });

  const totalSteps = 4;
  const progress = (step / totalSteps) * 100;

  const openWizard = () => {
    setIsOpen(true);
    analytics.projectStart('scoping_wizard');
  };

  const nextStep = () => {
    if (step < totalSteps) {
      setStep(step + 1);
      analytics.buttonClick(`wizard_step_${step}_next`, 'scoping_wizard');
    }
  };

  const prevStep = () => {
    if (step > 1) {
      setStep(step - 1);
      analytics.buttonClick(`wizard_step_${step}_prev`, 'scoping_wizard');
    }
  };

  const handleFeatureToggle = (feature: string) => {
    setProjectData(prev => ({
      ...prev,
      features: prev.features.includes(feature)
        ? prev.features.filter(f => f !== feature)
        : [...prev.features, feature]
    }));
  };

  const handleSubmit = () => {
    // Store project data locally for MVP
    const projectSubmission = {
      ...projectData,
      id: `project_${Date.now()}`,
      timestamp: Date.now(),
      status: 'pending_review'
    };

    const existingProjects = JSON.parse(localStorage.getItem('project_submissions') || '[]');
    existingProjects.push(projectSubmission);
    localStorage.setItem('project_submissions', JSON.stringify(existingProjects));

    analytics.formSubmit('project_scoping_wizard', true);
    setIsSubmitted(true);
  };

  const renderStep = () => {
    switch (step) {
      case 1:
        return (
          <div className="space-y-6">
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-2">
                Project Name *
              </label>
              <Input
                value={projectData.projectName}
                onChange={(e) => setProjectData(prev => ({ ...prev, projectName: e.target.value }))}
                placeholder="e.g., FitTracker Mobile App"
                required
              />
            </div>

            <div>
              <label className="block text-sm font-medium text-gray-700 mb-2">
                Project Type *
              </label>
              <Select
                value={projectData.projectType}
                onValueChange={(value) => setProjectData(prev => ({ ...prev, projectType: value }))}
              >
                <SelectTrigger>
                  <SelectValue placeholder="Select project type" />
                </SelectTrigger>
                <SelectContent>
                  {projectTypes.map((type) => (
                    <SelectItem key={type} value={type}>{type}</SelectItem>
                  ))}
                </SelectContent>
              </Select>
            </div>

            <div>
              <label className="block text-sm font-medium text-gray-700 mb-2">
                Project Description *
              </label>
              <Textarea
                value={projectData.description}
                onChange={(e) => setProjectData(prev => ({ ...prev, description: e.target.value }))}
                placeholder="Describe your vision, what problem it solves, and what you want to build..."
                rows={4}
                required
              />
            </div>

            <div>
              <label className="block text-sm font-medium text-gray-700 mb-2">
                Target Audience
              </label>
              <Input
                value={projectData.targetAudience}
                onChange={(e) => setProjectData(prev => ({ ...prev, targetAudience: e.target.value }))}
                placeholder="e.g., fitness enthusiasts, small business owners"
              />
            </div>
          </div>
        );

      case 2:
        return (
          <div className="space-y-6">
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-4">
                Features Needed (select all that apply)
              </label>
              <div className="grid grid-cols-2 gap-3">
                {commonFeatures.map((feature) => (
                  <div key={feature} className="flex items-center space-x-2">
                    <Checkbox
                      id={feature}
                      checked={projectData.features.includes(feature)}
                      onCheckedChange={() => handleFeatureToggle(feature)}
                    />
                    <label htmlFor={feature} className="text-sm text-gray-700">
                      {feature}
                    </label>
                  </div>
                ))}
              </div>
            </div>

            <div className="space-y-4">
              <div className="flex items-center space-x-2">
                <Checkbox
                  id="hasDesigns"
                  checked={projectData.hasDesigns}
                  onCheckedChange={(checked) => setProjectData(prev => ({ ...prev, hasDesigns: checked as boolean }))}
                />
                <label htmlFor="hasDesigns" className="text-sm text-gray-700">
                  I have existing designs/mockups
                </label>
              </div>

              <div className="flex items-center space-x-2">
                <Checkbox
                  id="hasContent"
                  checked={projectData.hasContent}
                  onCheckedChange={(checked) => setProjectData(prev => ({ ...prev, hasContent: checked as boolean }))}
                />
                <label htmlFor="hasContent" className="text-sm text-gray-700">
                  I have written content ready
                </label>
              </div>

              <div className="flex items-center space-x-2">
                <Checkbox
                  id="hasBackend"
                  checked={projectData.hasBackend}
                  onCheckedChange={(checked) => setProjectData(prev => ({ ...prev, hasBackend: checked as boolean }))}
                />
                <label htmlFor="hasBackend" className="text-sm text-gray-700">
                  I need backend/database development
                </label>
              </div>
            </div>
          </div>
        );

      case 3:
        return (
          <div className="space-y-6">
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-2">
                Budget Range *
              </label>
              <Select
                value={projectData.budget}
                onValueChange={(value) => setProjectData(prev => ({ ...prev, budget: value }))}
              >
                <SelectTrigger>
                  <SelectValue placeholder="Select budget range" />
                </SelectTrigger>
                <SelectContent>
                  {budgetRanges.map((range) => (
                    <SelectItem key={range} value={range}>{range}</SelectItem>
                  ))}
                </SelectContent>
              </Select>
            </div>

            <div>
              <label className="block text-sm font-medium text-gray-700 mb-2">
                Timeline *
              </label>
              <Select
                value={projectData.timeline}
                onValueChange={(value) => setProjectData(prev => ({ ...prev, timeline: value }))}
              >
                <SelectTrigger>
                  <SelectValue placeholder="Select timeline" />
                </SelectTrigger>
                <SelectContent>
                  {timelineOptions.map((timeline) => (
                    <SelectItem key={timeline} value={timeline}>{timeline}</SelectItem>
                  ))}
                </SelectContent>
              </Select>
            </div>

            <div>
              <label className="block text-sm font-medium text-gray-700 mb-2">
                Success Metrics
              </label>
              <Textarea
                value={projectData.successMetrics}
                onChange={(e) => setProjectData(prev => ({ ...prev, successMetrics: e.target.value }))}
                placeholder="How will you measure success? (e.g., user signups, revenue targets, etc.)"
                rows={3}
              />
            </div>
          </div>
        );

      case 4:
        return (
          <div className="space-y-6">
            <div className="text-center mb-6">
              <h3 className="text-lg font-semibold text-gray-900 mb-2">Review Your Project</h3>
              <p className="text-gray-600">Please review your project details before submitting</p>
            </div>

            <div className="bg-gray-50 rounded-lg p-4 space-y-4">
              <div>
                <h4 className="font-medium text-gray-900">{projectData.projectName}</h4>
                <Badge variant="secondary">{projectData.projectType}</Badge>
              </div>

              <div>
                <p className="text-sm text-gray-600 mb-2">Description:</p>
                <p className="text-sm text-gray-900">{projectData.description}</p>
              </div>

              {projectData.features.length > 0 && (
                <div>
                  <p className="text-sm text-gray-600 mb-2">Features:</p>
                  <div className="flex flex-wrap gap-2">
                    {projectData.features.map((feature) => (
                      <Badge key={feature} variant="outline">{feature}</Badge>
                    ))}
                  </div>
                </div>
              )}

              <div className="grid grid-cols-2 gap-4 text-sm">
                <div>
                  <span className="text-gray-600">Budget:</span>
                  <span className="ml-2 text-gray-900">{projectData.budget}</span>
                </div>
                <div>
                  <span className="text-gray-600">Timeline:</span>
                  <span className="ml-2 text-gray-900">{projectData.timeline}</span>
                </div>
              </div>
            </div>
          </div>
        );

      default:
        return null;
    }
  };

  if (!isOpen) {
    return (
      <Button
        onClick={openWizard}
        size="lg"
        className="bg-gradient-to-r from-blue-600 to-purple-600 hover:from-blue-700 hover:to-purple-700 text-white px-8 py-4 text-lg font-semibold rounded-xl shadow-lg hover:shadow-xl transition-all duration-300 transform hover:scale-105"
      >
        <Zap className="mr-2 h-5 w-5" />
        Start Project Wizard
      </Button>
    );
  }

  if (isSubmitted) {
    return (
      <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center p-4 z-50">
        <Card className="w-full max-w-md">
          <CardContent className="p-6">
            <div className="text-center">
              <div className="w-16 h-16 bg-green-100 rounded-full flex items-center justify-center mx-auto mb-4">
                <CheckCircle className="h-8 w-8 text-green-600" />
              </div>
              <h3 className="text-lg font-semibold text-gray-900 mb-2">Project Submitted!</h3>
              <p className="text-gray-600 mb-6">
                We'll review your project and connect you with qualified finishers within 24 hours.
              </p>
              <Button
                onClick={() => {
                  setIsOpen(false);
                  setIsSubmitted(false);
                  setStep(1);
                  setProjectData({
                    projectName: '',
                    projectType: '',
                    description: '',
                    budget: '',
                    timeline: '',
                    features: [],
                    hasDesigns: false,
                    hasContent: false,
                    hasBackend: false,
                    targetAudience: '',
                    successMetrics: ''
                  });
                }}
                className="w-full"
              >
                Close
              </Button>
            </div>
          </CardContent>
        </Card>
      </div>
    );
  }

  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center p-4 z-50">
      <Card className="w-full max-w-2xl max-h-[90vh] overflow-hidden">
        <CardHeader className="border-b">
          <div className="flex items-center justify-between">
            <div className="flex items-center space-x-3">
              <div className="w-10 h-10 bg-blue-100 rounded-lg flex items-center justify-center">
                <FileText className="h-5 w-5 text-blue-600" />
              </div>
              <div>
                <CardTitle>Project Scoping Wizard</CardTitle>
                <p className="text-sm text-gray-600">Step {step} of {totalSteps}</p>
              </div>
            </div>
            <Button
              variant="ghost"
              size="sm"
              onClick={() => setIsOpen(false)}
            >
              ×
            </Button>
          </div>
          <Progress value={progress} className="mt-4" />
        </CardHeader>

        <CardContent className="p-6 overflow-y-auto max-h-[60vh]">
          {renderStep()}
        </CardContent>

        <div className="border-t p-6">
          <div className="flex justify-between">
            <Button
              variant="outline"
              onClick={prevStep}
              disabled={step === 1}
            >
              <ArrowLeft className="h-4 w-4 mr-2" />
              Previous
            </Button>

            {step < totalSteps ? (
              <Button
                onClick={nextStep}
                disabled={
                  (step === 1 && (!projectData.projectName || !projectData.projectType || !projectData.description)) ||
                  (step === 3 && (!projectData.budget || !projectData.timeline))
                }
              >
                Next
                <ArrowRight className="h-4 w-4 ml-2" />
              </Button>
            ) : (
              <Button
                onClick={handleSubmit}
                className="bg-green-600 hover:bg-green-700"
              >
                Submit Project
                <CheckCircle className="h-4 w-4 ml-2" />
              </Button>
            )}
          </div>
        </div>
      </Card>
    </div>
  );
}